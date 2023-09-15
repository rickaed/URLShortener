package com.app.urlshortener.bdd;

import org.springframework.stereotype.Repository;

@Repository
public class BddRepository {
    private BddEntity bddEntity;

    public BddRepository(BddEntity bddEntity) {
        this.bddEntity = bddEntity;
    }

    // findByUrl
    public void findByLongUrl(String longUrl) {
        for (BddEntity iterable_element : iterable) {
            
        }


    }

    // saveUrl
    public void saveUrl() {

    }

    // findById(dycotomi)
    public void findById() {

    }

    // deleteUselessUrl
    public void deleteUrl() {

    }

    // sortUrlByShirtId(alphabetique)
}
