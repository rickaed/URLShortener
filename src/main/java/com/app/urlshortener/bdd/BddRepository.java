package com.app.urlshortener.bdd;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class BddRepository {
    private BddEntity bddEntity;
    private BddService bddService;

    public BddRepository(BddEntity bddEntity, BddService bddService) {
        this.bddEntity = bddEntity;
        this.bddService = bddService;
    }

    // findByUrl
    public ResponseEntity<?> findByLongUrl(String longUrl) {
        if (!bddService.exist(longUrl)) {
            saveUrl(longUrl);
        } else {
            ResponseEntity<?> invalidUrl = new ResponseEntity<>("invalid url", HttpStatus.BAD_REQUEST);
            return invalidUrl;
        }
        return null;
    }

    // saveUrl
    public void saveUrl(String longUrl) {
        // bddService.urlShorter()

    }

    // findById(dycotomi)
    public void findById() {

    }

    // deleteUselessUrl
    public void deleteUrl() {

    }

    // sortUrlByShirtId(alphabetique)
}
