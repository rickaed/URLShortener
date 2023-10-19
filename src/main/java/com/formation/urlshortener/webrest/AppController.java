package com.formation.urlshortener.webrest;

import com.formation.urlshortener.personalexception.InvalidUrlException;
import com.formation.urlshortener.personalexception.MissingUrlException;
import com.formation.urlshortener.usecase.RedirectUseCase;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class AppController {
    private final AppService appService;
    RedirectUseCase redirectUseCase;

    public AppController(AppService appService, RedirectUseCase redirectUseCase) {
        this.appService = appService;
        this.redirectUseCase = redirectUseCase;
    }
    @GetMapping("/info")
    public  String testLocalUrl(@RequestHeader ("Host") String host){
        return host;
        
    }

    /********************* POUR TEST *********************/
    // @GetMapping("/readAll")
    // public List<UrlEntity> readAll() throws StreamReadException,
    // DatabindException, IOException {
    // return bddRepository.readAllUrlEntities();
    // }
    /*****************************************************/

    @PostMapping("/links")
    public ResponseEntity<?> createNewUrlEntity(@RequestHeader ("Host") String host,@RequestBody String urlToAdd)
            throws URISyntaxException, IOException, InterruptedException, InvalidUrlException {
        System.out.println("@@@@@@@ récupération de l'url à ajouter" + urlToAdd);
        System.out.println("@@@@@@@ CREATION @@@@@@@");
        return appService.responseNewUrl(urlToAdd,host);

    }

    // redirect url
    @GetMapping("/{shortId}")
    public ResponseEntity<?> redirect(@PathVariable String shortId) throws IOException, MissingUrlException {
        return redirectUseCase.responseRedirect(shortId);
        

    }

    // delete url
    @DeleteMapping("/links/{id}")
    public HttpStatus delete(@RequestHeader("X-Removal-Token") String token, @PathVariable String id)
            throws IOException {
        System.out.println("@@@@@@@ DELETE @@@@@@@");
        return appService.responseDeleteUrl(id, token);

    }
}
