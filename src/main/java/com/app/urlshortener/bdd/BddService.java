package com.app.urlshortener.bdd;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class BddService {
   private final JsonGest jsonfile;

   public BddService(JsonGest jsonfile) {
      this.jsonfile = jsonfile;
   }

   public UrlEntity createUrlEntity(String longUrl) {

      UrlEntity newEntity = new UrlEntity();
      newEntity.setId(UUID.randomUUID().toString());
      newEntity.setShortId(genString(8));
      newEntity.setRealUrl(longUrl);
      newEntity.setToken(genString(40));
      newEntity.setDate(new Date());

      return newEntity;
   }

   // generation d'une suite de caractere alpohanumerique aléatoire
   // d'une longueur donnée
   public static String genString(int size) {
      int leftLimit = 48; // numeral '0'
      int rightLimit = 122; // letter 'z'
      Random random = new Random();

      return random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(size)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
   }

   /**
    * 
    * @param lookingFor issue de 2 fonction selon le besoin
    *                   soit l'url, soit l'id
    */
   public boolean exist(String lookingFor) throws IOException {
      System.out.println("@@@@@@@@@@@@@@@@@ exist? : " + lookingFor);
      boolean present = false;
      List<UrlEntity> urlEntities = jsonfile.readAllUrlEntities();
      for (UrlEntity urlEntity : urlEntities) {
         if (urlEntity.getRealUrl().equals(lookingFor) || urlEntity.getId().equals(lookingFor)||urlEntity.getShortId().equals(lookingFor)) {
            present = true;
            break;
         }
      }

      return present;
   }

   public boolean tokenValidator(UrlEntity urlEntity, String headToken) {
      System.out.println("@@@@@@@@ token " + headToken);
       return urlEntity.getToken().equals(headToken);
   }

}
