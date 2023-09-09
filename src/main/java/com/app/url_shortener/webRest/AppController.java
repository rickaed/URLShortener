package com.app.url_shortener.webRest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.url_shortener.urlShortener.UrlDto;

@CrossOrigin
@RestController
@RequestMapping("/links")
public class AppController {

    // service CreateShortUrl

    // service DeleteUrl

    @PostMapping
    public ResponseEntity<?> createShortUrl(@RequestBody UrlDto url) {
        // envoyer l'url au service CreateShortUrl
        // return response
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader String token) {
        // envoyer token et id au service DeleteUrl
        // return response
        return null;
    }
}
