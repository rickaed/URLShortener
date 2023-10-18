package com.formation.urlshortener.bdd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.formation.urlshortener.personalexception.InvalidUrlException;
import com.formation.urlshortener.webrest.Config;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Repository
public class BddRepository {

    private final Config config;

    public BddRepository(Config config) {
        this.config = config;
    }

    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    // init Bdd
    // cré un fichier vide si necessaire
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

    public Boolean notExist(URI newUrl)
            throws InvalidUrlException, IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();
        if (urlEntities.stream().noneMatch(entity -> entity.getRealUrl().equals(newUrl))) {
            System.out.println("@@@@@@@ "+newUrl+" n'est pas present en base");
            return true;
        } else
            throw new InvalidUrlException();
    }

    public List<UrlEntity> readAllUrlEntities() throws IOException {
        return mapper.readValue(initBdd(), new TypeReference<>() {
        });
    }

    public void addUrl(UrlEntity newEntity) throws IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();
        urlEntities.add(newEntity);
        saveUrls(urlEntities);
    }

    // saveUrl
    public void saveUrls(List<UrlEntity> updatedUrlEntities) throws IOException {

        File urlBdd = initBdd();
        mapper.writeValue(urlBdd, updatedUrlEntities);
    }

    // findById
    public UrlEntity findById(String id) throws IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();

        for (UrlEntity entity : urlEntities) {
            if (entity.getId().equals(id)) {
                return entity;
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
        urlEntities.removeIf(url -> url.getId().equals(urlEntity.getId()));
        // System.out.println(urlEntities);
        mapper.writeValue(initBdd(), urlEntities);
        // saveUrls(urlEntities);
    }

    // sortUrlByShirtId(alphabetique)

}
// public UrlEntity getModeByName(String modeName) throws IOException {

// for (UrlEntity m : urlEntities) {
// if (m.getModeName().equals(modeName)) {
// return m;
// }
// }
// return null;
// }

// public void putMode(@PathVariable String modeName, @RequestBody UrlEntity
// urlEntity) throws IOException {

// for (UrlEntity m : urlEntities) {
// if (m.getModeName().equals(modeName)) {
// m.setDescription(urlEntity.getDescription());
// }
// }
// objectMapper.writeValue(new File(pathMode), urlEntities);
// }