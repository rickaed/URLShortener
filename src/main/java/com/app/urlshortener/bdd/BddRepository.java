package com.app.urlshortener.bdd;

import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Repository
public class BddRepository {
    private final Config config;

    public BddRepository(Config config) {
        this.config = config;
    }

    // findByUrl
    public void findByLongUrl(String longUrl) {

    }

    // saveUrl
    public void saveUrl(UrlEntity newEntity) throws IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File urlBdd = initBdd();
        urlEntities.add(newEntity);
        mapper.writeValue(urlBdd, urlEntities);
    }

    // findById(dycotomi)
    public void findById() {

    }

    // deleteUselessUrl
    public void deleteUrl() {

    }

    /******************** POUR TEST *********************/

    public List<UrlEntity> readAllUrlEntities() throws IOException {
        File urlBdd = initBdd();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(urlBdd, new TypeReference<List<UrlEntity>>() {
        });
    }
    /****************************************************/

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
