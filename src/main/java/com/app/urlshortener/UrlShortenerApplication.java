package com.app.urlshortener;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.urlshortener.bdd.BddEntity;
import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class UrlShortenerApplication implements CommandLineRunner {
    private Config config;

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }

    public UrlShortenerApplication(Config config) {
        this.config = config;
    }

    @Override
    public void run(String[] args) throws IOException {

        // create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // read JSON file and convert to a bddEntity object
        BddEntity bddEntity = objectMapper.readValue(new File(config.getBddPath()), BddEntity.class);

        // print customer details for control
        System.out.println(bddEntity);
    }
}
