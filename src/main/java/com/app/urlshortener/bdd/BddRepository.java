package com.app.urlshortener.bdd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
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
    public void saveUrl(UrlEntity newEntity) throws StreamWriteException, DatabindException, IOException {
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

    /******************** POUR TEST *********************/  
    public List<UrlEntity> readAllUrlEntities() throws IOException  {
        File urlBdd= initBdd();
        ObjectMapper mapper = new ObjectMapper();

        List<UrlEntity> urlEntities = mapper.readValue(urlBdd, new TypeReference<List<UrlEntity>>() {
        });
        return urlEntities;
    }

    public File initBdd() throws IOException {
        File urlBdd = new File(config.getBddPath());
        // Create the file
        if (urlBdd.createNewFile()) {
            // write array
            FileWriter writer = new FileWriter(urlBdd);
            writer.write("[]");
            writer.close();
        }
        return urlBdd;
    }

    // sortUrlByShirtId(alphabetique)
}
