package com.app.urlshortener.bdd;

import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class JsonGest {

    private final Config config;

    public JsonGest(Config config) {
        this.config = config;
    }

    // lire en bdd ???
    // public void jsonReader() throws IOException {
    // ObjectMapper objectMapper = new ObjectMapper();
    // File myBdd = new File(config.getBddPath());

    // UrlEntity urlBdd = objectMapper.readValue(myBdd, UrlEntity.class);

    // }

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

    public List<UrlEntity> readAllUrlEntities() throws IOException {
        File urlBdd = initBdd();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(urlBdd, new TypeReference<>() {
        });
    }

}
