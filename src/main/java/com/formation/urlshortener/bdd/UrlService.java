package com.formation.urlshortener.bdd;

import com.formation.urlshortener.personalexception.InvalidTokenException;
import com.formation.urlshortener.personalexception.MissingUrlException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Service
public class UrlService {
   private final BddRepository bddRepository;

   public UrlService(BddRepository bddRepository) {
      this.bddRepository = bddRepository;
   }

   // public boolean creating(String urlToAdd) throws URISyntaxException {
   //    try {
   //       urlValidator(new URI(String.format(urlToAdd)));
   //       return true;

   //    } catch (InvalidUrlException e) {
   //       System.out.println("lancement de l'erreur");
   //       // for(StackTraceElement st:e.getStackTrace()){
   //       System.out.println(e.getStackTrace()[0].getMethodName());
   //       // }
   //       // e.SendToClient();

   //    }
   //    return false;

   // }

   // private void urlValidator(URI INCOMING_URL) throws InvalidUrlException {
   //    // if (!(INCOMING_URL.getScheme().equals("http") ||
   //    // INCOMING_URL.getScheme().equals("https"))) {
   //    if (INCOMING_URL.getScheme().equals("ftp")) {
   //       System.out.println("@@@@@@@ doit renvoyer erreur 'InvalidUrlExcption' urlValidator");
   //       throw new InvalidUrlException();
   //    } else {
   //       System.out.println("@@@@@@@ url " + INCOMING_URL + " valide !");
   //       try {
   //          notExist(INCOMING_URL);

   //       } catch (InvalidUrlException e) {
   //          System.out.println(e.getStackTrace()[0].getMethodName());
   //       }
   //    }

   // }

   // public void notExist(URI newUrl) throws InvalidUrlException {
   //    List<UrlEntity> urlEntities = bddRepository.readAllUrlEntities();

   //    for (UrlEntity urlEntity : urlEntities) {

   //       if (urlEntity.getRealUrl().equals(newUrl)) {
   //          throw new InvalidUrlException();
   //       }
   //    }
   //    System.out.println("@@@@@@@ " + newUrl + " à enregistrer");
   //    try {
   //       ping(newUrl);

   //    } catch (Exception e) {
   //       System.out.println(e.getStackTrace()[0].getMethodName());
   //    }
   // }

   // private void ping(URI newUrl) throws IOException, InterruptedException, InvalidUrlException {
   //    final var request = HttpRequest.newBuilder(newUrl)
   //          .GET()
   //          .build();
   //    final var response = HttpClient.newHttpClient()
   //          .send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
   //    if (response.statusCode() != 200) {
   //       System.out.printf("@@@@@@@ Status code %d ", response.statusCode());
   //       System.out.println("@@@@@@@ Fausse URL " + newUrl);
   //       throw new InvalidUrlException();

   //    }

   // }

   public ResponseEntity<?> exist(String shortId) throws IOException {
      System.out.println("@@@@@@@@@@@@@@@@@ exist? : " + shortId);

      List<UrlEntity> urlEntities = bddRepository.readAllUrlEntities();
      for (UrlEntity urlEntity : urlEntities) {

         if (urlEntity.getShortId().equals(shortId)) {
            return redirectBuilder(shortId, urlEntities);
         }

      }
      return null;

   }

   

  

   public ResponseEntity<?> redirectBuilder(String shortId, List<UrlEntity> urlEntites)
         throws IOException {

      URI redirect = bddRepository.findByShortId(shortId);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setLocation(redirect);
      return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
   }

   public boolean tokenValidator(UrlEntity urlEntity, String headToken) {
      System.out.println("@@@@@@@@ token " + headToken);
      return urlEntity.getToken().equals(headToken);
   }

}
