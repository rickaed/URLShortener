package com.app.urlshortener.webRest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.urlshortener.url.UrlDto;

@RestController
public class AppController {

    // create link
    @PostMapping("/links")
    public ResponseEntity<?> createShortUrl(@RequestBody UrlDto url) {
        // urlService
        // message de retour
        return null;
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
