package com.app.urlshortener.url;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.urlshortener.bdd.BddRepository;
import com.app.urlshortener.bdd.BddService;


@Service
public class UrlService {
    private  BddRepository bddRepository;
    private  BddService bddService;

    public UrlService(BddRepository bddRepository, BddService bddService) {
        this.bddRepository = bddRepository;
        this.bddService = bddService;
    }

    boolean goodUrl = false;

    // public ResponseEntity<?> response(String url) {
    //     try {
    //         goodUrl = validUrl(url);
    //     } catch (URISyntaxException e) {
    //         e.printStackTrace();
    //     } 
    //     if (goodUrl){
    //        boolean urlStore = bddService.exist(url)
    //     }
    //     /**
    //      * if url !exist
    //      * "id": "2bffc207-6fd5-4e1d-afc3-b09b4d380416",
    //      * "short-id": "aX8Pm2wt",
    //      * "real-url": "https://url-a-raccourcir.com/"
    //      * 
    //      * else =>
    //      * return String "invalid url" code : 400
    //      */
    //     return null;
    // }

    // verifie que l'url commence par http(s)
    private final static boolean validUrl(String url) throws URISyntaxException {
        URI incomingUrl = new URI(url);
        if (incomingUrl.getScheme().equals("http") || incomingUrl.getScheme().equals("https")) {
            return true;
        } else {
            return false;
        }
    }

}
