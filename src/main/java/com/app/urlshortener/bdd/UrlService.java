package com.app.urlshortener.bdd;

import com.app.urlshortener.personalexception.InvalidTokenException;
import com.app.urlshortener.personalexception.InvalidUrlException;
import com.app.urlshortener.personalexception.MissingUrlException;
import com.app.urlshortener.usecase.CreateUrlUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

@Service
public class UrlService {
   private final BddRepository bddRepository;
   private final CreateUrlUseCase createUrlUseCase;

   public UrlService(BddRepository bddRepository, CreateUrlUseCase createUrlUseCase) {
      this.bddRepository = bddRepository;
      this.createUrlUseCase = createUrlUseCase;
   }

   public UrlEntity creating(URI INCOMING_URL) throws InvalidUrlException, IOException, InterruptedException {
      urlValidator(INCOMING_URL);
      exist(INCOMING_URL);
      ping(INCOMING_URL);
      return createUrlUseCase.createUrlEntity(INCOMING_URL, bddRepository.readAllUrlEntities());

   }

   private void urlValidator(URI INCOMING_URL) throws InvalidUrlException {

      if (!(INCOMING_URL.getScheme().equals("http") ||
            INCOMING_URL.getScheme().equals("https"))) {
         System.out.println("@@@@@@@ URL INVALIDE");
         throw new InvalidUrlException("invalid url");
      }
      System.out.println("@@@@@@@ URL VALIDE");
   }

   public void exist(URI newUrl) throws InvalidUrlException, IOException {
      List<UrlEntity> urlEntities = bddRepository.readAllUrlEntities();
      for (UrlEntity urlEntity : urlEntities) {
         if (urlEntity.getRealUrl().equals(newUrl)) {
            System.out.println("@@@@@@@" + newUrl + " existe déjà");
            throw new InvalidUrlException("invalid url");
         }
      }
      System.out.println("@@@@@@@ " + newUrl + " à enregistrer");
   }

   private void ping(URI newUrl) throws IOException, InterruptedException, InvalidUrlException {
      final var request = HttpRequest.newBuilder(newUrl)
            .GET()
            .build();
      final var response = HttpClient.newHttpClient()
            .send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
      if (response.statusCode() != 200) {
         System.out.printf("@@@@@@@ Status code %d ", response.statusCode());
         System.out.println("@@@@@@@ Fausse URL " + newUrl);
         throw new InvalidUrlException("invalid url");
      }
      System.out.println("@@@@@@@ récuperation status 200");
   }

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

   public void exist(String id, String token) throws IOException, MissingUrlException {
      List<UrlEntity> urlEntities = bddRepository.readAllUrlEntities();
      for (UrlEntity urlEntity : urlEntities) {
         if (urlEntity.getId().equals(id)) {
            System.out.println("@@@@@@@ compare : " + urlEntity.getId() + " " + id);
            return;
         }

      }
      throw new MissingUrlException("Pas Trouvé");
   }

   public void delete(String id, String token) throws InvalidTokenException, IOException {

      UrlEntity urlEntity = bddRepository.findById(id);

      if (!tokenValidator(urlEntity, token)) {
         throw new InvalidTokenException("bad token");

      } else {
         bddRepository.deleteUrl(urlEntity);

      }

   }

   public ResponseEntity<?> redirectBuilder(String shortId, List<UrlEntity> urlEntites)
         throws IOException {

      URI redirect = bddRepository.findByShortId(shortId, urlEntites);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setLocation(redirect);
      return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
   }

   public boolean tokenValidator(UrlEntity urlEntity, String headToken) {
      System.out.println("@@@@@@@@ token " + headToken);
      return urlEntity.getToken().equals(headToken);
   }

}
