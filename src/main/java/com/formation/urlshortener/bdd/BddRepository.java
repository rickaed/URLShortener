package com.formation.urlshortener.bdd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.formation.urlshortener.personalexception.InvalidUrlException;
import com.formation.urlshortener.personalexception.MissingUrlException;
import com.formation.urlshortener.webrest.Config;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

@Repository
public class BddRepository {

    private final Config config;   
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm");

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
            System.out.println("@@@@@@@ " + newUrl + " n'est pas present en base");
            return true;
        } else
            throw new InvalidUrlException();
    }

    public Boolean idExist(String id) throws MissingUrlException, IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();
        System.out.println("@@@@@@@ check ID");
        if (urlEntities.stream().anyMatch(entity -> entity.getId().equals(id))) {
            System.out.println("@@@@@@@ " + id + " va etre supprimé");
            return true;
        } else
            throw new MissingUrlException();
    }

    public Boolean shortIdExist(String shortid) throws MissingUrlException, IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();
        System.out.println("@@@@@@@ check ShortID");
        if (urlEntities.stream().anyMatch(entity -> entity.getShortId().equals(shortid))) {
            System.out.println("@@@@@@@ redirection de " + shortid);
            return true;
        } else
            throw new MissingUrlException();
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

    public URI findByShortId(String shortId) throws IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();
        int i = 0;
        for (UrlEntity urlEntity : urlEntities) {
            if (urlEntity.getShortId().equals(shortId)) {
                URI realUrl = urlEntity.getRealUrl();

                // a decouper ********/
                // updateDate()

                urlEntity.setDate(dateFormat.format(new Date()));

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
    public void deleteUrl(UrlEntity urlEntityToDelete) throws IOException {
        List<UrlEntity> urlEntities = readAllUrlEntities();
        urlEntities.removeIf(url -> url.getId().equals(urlEntityToDelete.getId()));
        System.out.println("@@@@@@@ Suppression de : " + urlEntityToDelete);
        // System.out.println(urlEntities);
        mapper.writeValue(initBdd(), urlEntities);
        // saveUrls(urlEntities);
    }

}
