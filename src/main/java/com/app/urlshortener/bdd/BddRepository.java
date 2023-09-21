package com.app.urlshortener.bdd;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BddRepository {
    private Config config;

    public BddRepository(Config config) {
        this.config = config;
    }

    // findByUrl
    public void findByLongUrl(String longUrl) {

    }

    // saveUrl
    public void saveUrl(BddEntity newEntity) throws StreamWriteException, DatabindException, IOException {
        File myBdd = new File(config.getBddPath());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(myBdd, newEntity);

    }

    // findById(dycotomi)
    public void findById() {

    }

    // deleteUselessUrl
    public void deleteUrl() {

    }

    // sortUrlByShirtId(alphabetique)
}
