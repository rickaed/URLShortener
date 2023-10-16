package com.app.urlshortener.webrest;

import com.app.urlshortener.bdd.BddRepository;
import com.app.urlshortener.bdd.UrlService;
import com.app.urlshortener.personalexception.InvalidTokenException;
import com.app.urlshortener.personalexception.InvalidUrlException;
import com.app.urlshortener.personalexception.MissingUrlException;
import com.app.urlshortener.bdd.UrlEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.html.parser.Entity;

@RestController
public class AppController {
    private final UrlService urlService;
    private final BddRepository bddRepository;

    public AppController(UrlService urlService, BddRepository bddRepository) {
        this.urlService = urlService;
        this.bddRepository = bddRepository;

    }

    /********************* POUR TEST *********************/
    @GetMapping("/readAll")
    public List<UrlEntity> readAll() throws IOException {
        return bddRepository.readAllUrlEntities();
    }

    /*****************************************************/

    /**
     * création de lien
     * 
     * @throws InterruptedException
     *
     */
    @PostMapping("/links")

    public ResponseEntity<?> createShortId(@RequestBody String urlToAdd)
            throws URISyntaxException, IOException, InterruptedException {
        System.out.println("@@@@@@@ récupération de l'url à ajouter" + urlToAdd);
        urlToAdd = urlToAdd.replace("\"", "");
        final URI INCOMING_URL = new URI(String.format(urlToAdd));
        try {
            UrlEntity newEntity = urlService.creating(INCOMING_URL);

            HashMap<String, String> responseBody = new HashMap<>();
            responseBody.put("id", newEntity.getId());
            responseBody.put("short-id", newEntity.getShortId());
            responseBody.put("real-url", newEntity.getRealUrl().toString());
            return ResponseEntity.status(HttpStatus.CREATED).header("X-Removal-Token", newEntity.getToken())
                    .body(responseBody);
            // return new ResponseEntity<>(responseBody, responseHeader,
            // HttpStatus.CREATED);

        } catch (InvalidUrlException e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid url");

        }

    }

    // redirect url
    @GetMapping("/{shortId}")
    public Object redirect(@PathVariable String shortId) throws IOException {
        return urlService.exist(shortId);

    }

    // delete url
    @DeleteMapping("/links/{id}")
    public ResponseEntity<?> delete(@RequestHeader("X-Removal-Token") String token, @PathVariable String id) throws IOException
            {

        System.out.println("@@@@@@@ DELETE @@@@@@@");
        try {
            urlService.exist(id, token);
            urlService.delete(id, token);
             return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MissingUrlException e) {
            e.printStackTrace();
              return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidTokenException e) {
            e.printStackTrace();
             return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

       

    }
}
