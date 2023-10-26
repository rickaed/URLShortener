package com.formation.urlshortener.usecase;

import com.formation.urlshortener.bdd.BddRepository;
import com.formation.urlshortener.personalexception.MissingUrlException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

@Service
public class RedirectUseCase {
    private final BddRepository bddRepository;

    RedirectUseCase(BddRepository bddRepository) {
        this.bddRepository = bddRepository;
    }

    public ResponseEntity<?> responseRedirect(String shortId) throws IOException, MissingUrlException {

        return this.bddRepository.findByWhatever()
            .map(shortUrl -> {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setLocation(shortUrl.getRealUrl());
                return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());

        /*if (bddRepository.shortIdExist(shortId)) {

            URI redirect = bddRepository.findByShortId(shortId);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirect);
            return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
        }
        return null;*/

    }
}
