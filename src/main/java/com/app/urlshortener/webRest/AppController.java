package com.app.urlshortener.webRest;

import com.app.urlshortener.bdd.BddRepository;
import com.app.urlshortener.bdd.BddService;
import com.app.urlshortener.bdd.JsonGest;
import com.app.urlshortener.bdd.UrlEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

@RestController
public class AppController {
    private final AppService appService;
    private final BddService bddService;
    private final BddRepository bddRepository;
    private final JsonGest jsonFile;

    public AppController(AppService appService, BddService bddService, BddRepository bddRepository, JsonGest jsonFile) {
        this.appService = appService;
        this.bddService = bddService;
        this.bddRepository = bddRepository;
        this.jsonFile = jsonFile;

    }

    /********************* POUR TEST *********************/
    @GetMapping("/readAll")
    public List<UrlEntity> readAll() throws IOException {
        return jsonFile.readAllUrlEntities();
    }

    /*****************************************************/

    // create link
    @PostMapping("/links")
    public ResponseEntity<?> createShortId(@RequestBody String urlToAdd) throws URISyntaxException, IOException {
        ResponseEntity<?> response = new ResponseEntity<>("invalid url", HttpStatus.BAD_REQUEST);
        urlToAdd = urlToAdd.replace("\"", "");
        // si format url valide
        if (appService.validUrl(urlToAdd)) {
            // System.out.println("@@@@@@@@@@@@@@@@@ url valide");
            // si l'url n'existe pas en bdd
            if (!bddService.exist(urlToAdd)) {
                // System.out.println("@@@@@@@@@@@@@@ url not exist");

                UrlEntity newEntity = bddService.createUrlEntity(urlToAdd);
                bddRepository.saveUrl(newEntity);

                HttpHeaders responseHeader = new HttpHeaders();
                responseHeader.set("X-Removal-Token", newEntity.getToken());

                HashMap<String, String> responseBody = new HashMap<>();
                responseBody.put("id", newEntity.getId());
                responseBody.put("short-id", newEntity.getShortId());
                responseBody.put("real-url", newEntity.getRealUrl());

                response = new ResponseEntity<>(responseBody, responseHeader, HttpStatus.CREATED);

            }
        }
        return response;
    }

    // redirect url
    @GetMapping("/{shortId}")
    public void responseUrl(@PathVariable String shortId) {

        // java.net.URI location = new java.net.URI("../index.jsp?msg=A_User_Added");
        // return Response.temporaryRedirect(location).build()
    }

    // delete url
    @DeleteMapping("/links/{id}")
    public ResponseEntity<?> delete(@RequestHeader("X-Removal-Token") String token, @PathVariable String id)
            throws IOException {
        ResponseEntity<?> response;
        System.out.println("@@@@@@@ DELETE @@@@@@@");
        if (bddService.exist(id)) {
            UrlEntity urlEntity = bddRepository.findById(id);
            if (bddService.tokenValidator(urlEntity, token)) {
                System.out.println(" @@@@@@@ Token Valide");
                bddRepository.deleteUrl(urlEntity);
                response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                System.out.println("@@@@@@@ FORBIDDEN");
                response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            System.out.println("@@@@@@@ Not found");
        }
        return response;
    }
}
