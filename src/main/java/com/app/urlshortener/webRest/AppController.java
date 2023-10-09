package com.app.urlshortener.webRest;

import com.app.urlshortener.bdd.BddRepository;
import com.app.urlshortener.bdd.BddService;
import com.app.urlshortener.bdd.UrlEntity;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

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
    public ResponseEntity<?> createShortId(@RequestBody String urlToAdd) throws URISyntaxException, IOException {
        ResponseEntity<?> response = new ResponseEntity<>("invalid url", HttpStatus.BAD_REQUEST);
        urlToAdd = urlToAdd.replace("\"", "");
        // si format url valide
        if (appService.validUrl(urlToAdd)) {
            System.out.println("@@@@@@@@@@@@@@@@@ url valide");
            // si l'url n'existe pas en bdd
            if (!bddService.exist(urlToAdd)) {
                System.out.println("@@@@@@@@@@@@@@ url not exist");

                HashMap<String, String> answer = bddService.createUrlEntity(urlToAdd);
                HttpHeaders responseHeader = new HttpHeaders();
                responseHeader.set("X-Removal-Token", answer.get("X-Removal-Token"));
                answer.remove("X-Removal-Token");
                var responseBody = answer;
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
    public ResponseEntity<?> delete(@RequestHeader String token, @PathVariable String id) {
        // envoyer token et id au service DeleteUrl
        // return response
        return null;
    }
}
