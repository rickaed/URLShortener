package com.app.urlshortener.url;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.urlshortener.bdd.BddRepository;
import com.app.urlshortener.bdd.BddService;

@Service
public class UrlService {
    private BddRepository bddRepository;
    private BddService bddService;

    public UrlService(BddRepository bddRepository, BddService bddService) {
        this.bddRepository = bddRepository;
        this.bddService = bddService;
    }

    boolean goodUrl = false;

    // reponse à l'ajout d'url
    public ResponseEntity<?> response(String urlToAdd) {
        ResponseEntity<?> invalidUrl = new ResponseEntity<>("invalid url", HttpStatus.BAD_REQUEST);
        try {
            // verifier la structure de urlToAdd
            goodUrl = validUrl(urlToAdd);
        } catch (URISyntaxException err) {
            err.printStackTrace();
            return invalidUrl;
        }
        // si urlToAdd est valide
        // verifier la presence en bdd
        if (goodUrl) {
            bddRepository.findByLongUrl(urlToAdd);
        }
        /**
         * if url !exist
         * "id": "2bffc207-6fd5-4e1d-afc3-b09b4d380416",
         * "short-id": "aX8Pm2wt",
         * "real-url": "https://url-a-raccourcir.com/"
         * 
         * else =>
         * return String "invalid url" code : 400
         */
        return null;
    }

    // verifie que l'url commence par http(s)
    private final static boolean validUrl(String longUrl) throws URISyntaxException {
        URI incomingUrl = new URI(longUrl);
        if (incomingUrl.getScheme().equals("http") || incomingUrl.getScheme().equals("https")) {
            return true;
        } else {
            return false;
        }
    }

}
