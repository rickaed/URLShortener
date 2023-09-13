package com.app.urlshortener.webRest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.urlshortener.url.UrlService;


@RestController
public class AppController {
    private UrlService urlService;

    public AppController(UrlService urlService) {
        this.urlService = urlService;
    }

    // create link
    @PostMapping("/links")
    public ResponseEntity<?> createShortUrl(@RequestBody String url) {
        return urlService.response(url);
        // urlService
        // message de retour
        
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
