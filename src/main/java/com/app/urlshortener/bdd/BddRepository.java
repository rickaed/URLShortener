package com.app.urlshortener.bdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Repository
public class BddRepository {
    private final JsonGest jsonFile;

    public BddRepository(JsonGest jsonFile) {
        this.jsonFile = jsonFile;
    }

    // findByUrl
    public void findByLongUrl(String longUrl) {

    }

    // saveUrl
    public void saveUrl(UrlEntity newEntity) throws IOException {
        List<UrlEntity> urlEntities = jsonFile.readAllUrlEntities();
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File urlBdd = jsonFile.initBdd();
        urlEntities.add(newEntity);
        mapper.writeValue(urlBdd, urlEntities);
    }

    // findById
    public UrlEntity findById(String id) throws IOException {
        List<UrlEntity> urlEntities = jsonFile.readAllUrlEntities();
        for (UrlEntity urlEntity : urlEntities) {
            if (urlEntity.getId().equals(id)) {
                System.out.println("@@@@@@@@ mon elem à supp " + urlEntity);
                return urlEntity;
            }
        }
        return null;
    }

    public String findByShortId(String shortId) throws IOException {
        List<UrlEntity> urlEntities = jsonFile.readAllUrlEntities();
        String realUrl = "";
        int i = 0;
        for (UrlEntity urlEntity : urlEntities) {
            if (urlEntity.getShortId().equals(shortId)) {
                realUrl = urlEntity.getRealUrl();

                // a decouper ********/
                // updateDate()
                urlEntity.setDate(new Date());
                // save()
                urlEntities.set(i, urlEntity);
                // SAVE
                ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                File urlBdd = jsonFile.initBdd();
                mapper.writeValue(urlBdd, urlEntities);
                // System.out.println(urlEntities);
            }
            i++;

        }
        return realUrl;

    }

    // deleteUselessUrl
    public void deleteUrl(UrlEntity urlEntity) throws IOException {
        List<UrlEntity> urlEntities = jsonFile.readAllUrlEntities();
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File urlBdd = jsonFile.initBdd();
        System.out.println(urlEntities.remove(urlEntity));

        urlEntities.removeIf(urlEntity1 -> urlEntity1.getId().equals(urlEntity.getId()));
        System.out.println(urlEntities);
        mapper.writeValue(urlBdd, urlEntities);
    }

    // sortUrlByShirtId(alphabetique)
}
