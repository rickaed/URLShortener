package com.app.urlshortener.bdd;

import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class BddService {
   private final BddRepository bddRepository;
   private final Config config;

   public BddService(BddRepository bddRepository, Config config) {
      this.bddRepository = bddRepository;
      this.config = config;
   }

   public HashMap<String, String> createUrlEntity(String longUrl)
           throws IOException {

      UrlEntity newEntity = new UrlEntity();
      newEntity.setId(UUID.randomUUID().toString());
      newEntity.setShortId(genString(8));
      newEntity.setRealUrl(longUrl);
      newEntity.setToken(genString(40));
      newEntity.setDate(new Date());

      bddRepository.saveUrl(newEntity);

      HashMap<String, String> map = new HashMap<String, String>();
      map.put("id", newEntity.getId());
      map.put("short-id", newEntity.getShortId());
      map.put("real-url", newEntity.getRealUrl());
      map.put("X-Removal-Token",newEntity.getToken());

      return map;
   }

   // lire en bdd ???
   public void jsonReader() throws IOException {
      ObjectMapper objectMapper = new ObjectMapper();
      File myBdd = new File(config.getBddPath());

      UrlEntity urlBdd = objectMapper.readValue(myBdd, UrlEntity.class);

   }

   // generation d'une suite de caractere alpohanumerique aléatoire
   // d'une longueur donnée
   public static String genString(int size) {
      int leftLimit = 48; // numeral '0'
      int rightLimit = 122; // letter 'z'
      int targetStringLength = size;
      Random random = new Random();

      String generatedString = random.ints(leftLimit, rightLimit + 1)
              .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
              .limit(targetStringLength)
              .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
              .toString();

      return generatedString;
   }



   public boolean exist(String longUrl) throws IOException {
      System.out.println("@@@@@@@@@@@@@@@@@ exist? : " + longUrl);
      ObjectMapper mapper = new ObjectMapper();
      List<UrlEntity> urlEntities= bddRepository.readAllUrlEntities();
      boolean present = false;
      for (UrlEntity urlEntity : urlEntities) {
         if (urlEntity.getRealUrl().equals(longUrl)) {
            present = true;
            break;
         }
      }

      return present;
   }

}
