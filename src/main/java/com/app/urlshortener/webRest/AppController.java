package com.app.urlshortener.webRest;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.urlshortener.bdd.BddRepository;
import com.app.urlshortener.bdd.BddService;
import com.app.urlshortener.bdd.UrlEntity;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

@RestController
public class AppController {
    private AppService appService;
    private BddService bddService;
    private BddRepository bddRepository;

    public AppController(AppService appService, BddService bddService, BddRepository bddRepository) {
        this.appService = appService;
        this.bddService = bddService;
        this.bddRepository = bddRepository;
    }

    /********************* POUR TEST *********************/
    @GetMapping("/readAll")
    public List<UrlEntity> readAll() throws StreamReadException, DatabindException, IOException {
        return bddRepository.readAllUrlEntities();
    }

    // create link
    @PostMapping("/links")
    public ResponseEntity<?> createShortId(@RequestBody String urlToAdd) {
        ResponseEntity<?> response = new ResponseEntity<>("invalid url", HttpStatus.BAD_REQUEST);
        urlToAdd = urlToAdd.replace("\"", "");
        // si format url valide
        if (appService.validUrl(urlToAdd)) {
            System.out.println("@@@@@@@@@@@@@@@@@ url valide");
            // si l'url n'existe pas en bdd
            if (!bddService.exist(urlToAdd)) {
                System.out.println("@@@@@@@@@@@@@@ url not exist");

                HashMap<String, String> bodyrep = bddService.createUrlEntity(urlToAdd);
                response = new ResponseEntity<>(bodyrep, HttpStatus.CREATED);
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
    public ResponseEntity<?> delete(@RequestHeader String token, @PathVariable String id) {
        // envoyer token et id au service DeleteUrl
        // return response
        return null;
    }
}
