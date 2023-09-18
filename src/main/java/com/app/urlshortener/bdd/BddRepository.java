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
        if (bddService.exist(longUrl)) {
            ResponseEntity<?> invalidUrl = new ResponseEntity<>("invalid url", HttpStatus.BAD_REQUEST);
            return invalidUrl;
        } else {
            saveUrl(longUrl);
        }
        return null;
    }

    // saveUrl
    public void saveUrl(String longUrl) {

    }

    // findById(dycotomi)
    public void findById() {

    }

    // deleteUselessUrl
    public void deleteUrl() {

    }

    // sortUrlByShirtId(alphabetique)
}
