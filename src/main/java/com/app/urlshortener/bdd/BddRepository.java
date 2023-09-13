package com.app.urlshortener.bdd;

import org.springframework.stereotype.Repository;

@Repository
public class BddRepository {
    private  BddEntity bddEntity;

    public BddRepository(BddEntity bddEntity) {
        this.bddEntity = bddEntity;
    }

    // findById(dycotomi)
    // findByUrl
    // saveNewUrl
    // deleteUselessUrl
    // sortUrlByShirtId(alphabetique)
}
