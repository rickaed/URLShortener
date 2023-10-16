package com.app.urlshortener.bdd;

import com.app.urlshortener.webrest.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Repository
public class BddRepository {

    private static   Config config;

    public BddRepository(Config config) {
        BddRepository.config = config;
    }

    // init Bdd
    // cré un fichier vide si necessaire
    public static File initBdd() throws IOException {
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

    public void addUrl(UrlEntity newEntity, List<UrlEntity> urlEntities) throws IOException {
        urlEntities.add(newEntity);
        saveUrls(urlEntities);

    }

    // saveUrl
    public static void saveUrls(List<UrlEntity> urlEntities) throws IOException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File urlBdd = initBdd();
        mapper.writeValue(urlBdd, urlEntities);
    }

    // findById
    public UrlEntity findById(String id) throws IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();
        for (UrlEntity urlEntity : urlEntities) {
            if (urlEntity.getId().equals(id)) {
                System.out.println("@@@@@@@@ mon elem à supp " + urlEntity);
                return urlEntity;
            }
        }
        return null;
    }

    public URI findByShortId(String shortId, List<UrlEntity> urlEntities) throws IOException {
        
        int i = 0;
        for (UrlEntity urlEntity : urlEntities) {
            if (urlEntity.getShortId().equals(shortId)) {
               URI realUrl = urlEntity.getRealUrl();

                // a decouper ********/
                // updateDate()
                urlEntity.setDate(new Date());
                // save()
                urlEntities.set(i, urlEntity);
                // SAVE

                saveUrls(urlEntities);
                // System.out.println(urlEntities);
                return realUrl;
                
            }
            i++;

        }
        return null;
        

    }

    // deleteUselessUrl
    public void deleteUrl(UrlEntity urlEntity) throws IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();

        urlEntities.removeIf(urlEntity1 -> urlEntity1.getId().equals(urlEntity.getId()));
        System.out.println(urlEntities);

        saveUrls(urlEntities);
    }

    // sortUrlByShirtId(alphabetique)
}
