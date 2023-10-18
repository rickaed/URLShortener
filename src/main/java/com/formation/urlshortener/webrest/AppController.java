package com.formation.urlshortener.webrest;

import com.formation.urlshortener.bdd.BddRepository;
import com.formation.urlshortener.bdd.UrlEntity;
import com.formation.urlshortener.bdd.UrlService;
import com.formation.urlshortener.personalexception.InvalidTokenException;
import com.formation.urlshortener.personalexception.InvalidUrlException;
import com.formation.urlshortener.personalexception.MissingUrlException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class AppController {
    private final UrlService urlService;
    private final BddRepository bddRepository;
    private final AppService appService;

    public AppController(UrlService urlService, BddRepository bddRepository, 
            AppService appService) {
        this.urlService = urlService;
        this.bddRepository = bddRepository;
        this.appService = appService;
    }

    /********************* POUR TEST ***/

    @GetMapping("/readAll")
    public List<UrlEntity> readAll() throws StreamReadException, DatabindException, IOException {
        return bddRepository.readAllUrlEntities();
    }

    @PostMapping("/links")
    public ResponseEntity<?> createNewUrlEntity(@RequestBody String urlToAdd)
            throws URISyntaxException, IOException, InterruptedException, InvalidUrlException {
        System.out.println("@@@@@@@ récupération de l'url à ajouter" + urlToAdd);
        return appService.responseNewUrl(urlToAdd);

    }

    // redirect url
    @GetMapping("/{shortId}")
    public Object redirect(@PathVariable String shortId) throws IOException {
        return urlService.exist(shortId);

    }

    // delete url
    @DeleteMapping("/links/{id}")
    public ResponseEntity<?> delete(@RequestHeader("X-Removal-Token") String token, @PathVariable String id)
            throws IOException {

        System.out.println("@@@@@@@ DELETE @@@@@@@");
        try {
            urlService.existId(id);
            urlService.delete(id, token);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MissingUrlException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidTokenException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
