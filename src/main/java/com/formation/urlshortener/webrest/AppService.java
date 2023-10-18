package com.formation.urlshortener.webrest;

import com.formation.urlshortener.bdd.BddRepository;
import com.formation.urlshortener.personalexception.InvalidUrlException;
import com.formation.urlshortener.usecase.CreateUrlUseCase;
import com.formation.urlshortener.usecase.Mapper;
import com.formation.urlshortener.usecase.NewEntityDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Service
public class AppService {
    BddRepository bddRepository;
    CreateUrlUseCase createUrlUseCase;
    Mapper mapper;

    AppService(BddRepository bddRepository, Mapper mapper, CreateUrlUseCase createUrlUseCase) {
        this.bddRepository = bddRepository;
        this.mapper = mapper;
        this.createUrlUseCase = createUrlUseCase;

    }

    String[] schemes = { "http", "https" };

    public  ResponseEntity<?> responseNewUrl(String newUrl)
            throws URISyntaxException, InvalidUrlException, IOException, InterruptedException {
        newUrl = newUrl.replace("\"", "");
        URI newUri = new URI(String.format(newUrl));
        if (validateUrl(newUri) && bddRepository.notExist(newUri) && pingUrl(newUri)) {

            System.out.println("@@@@@@@ " + newUri + " à passé tout les test");

            HashMap<String, Object> elemToSend = createUrlUseCase.createUrlEntity(newUri);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("X-Removal-Token", (String) elemToSend.get("token"))
                    .body((NewEntityDto)elemToSend.get("entity"));

        }
        return null;
    }

    private boolean validateUrl(URI newUri) throws InvalidUrlException {
        for (String scheme : schemes) {
            if (newUri.getScheme().equals(scheme)) {
                System.out.println("@@@@@@@ le scheme de " + newUri + " est validé");
                return true;
            }
        }
        throw new InvalidUrlException();
    }

    private boolean pingUrl(URI newUri) throws IOException, InterruptedException, InvalidUrlException {
        final var request = HttpRequest.newBuilder(newUri)
                .GET()
                .build();
        final var response = HttpClient.newHttpClient()
                .send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (response.statusCode() == 200) {
            System.out.printf("@@@@@@@ Status code %d ", response.statusCode());

            return true;
        }
        throw new InvalidUrlException();
    }
}
