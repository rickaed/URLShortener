package com.app.urlshortener.url;

import org.springframework.stereotype.Service;

import com.app.urlshortener.bdd.BddRepository;

@Service
public class UrlService {
    private BddRepository bddRepository;

    public UrlService(BddRepository bddRepository) {
        this.bddRepository = bddRepository;
    }

    boolean goodUrl = false;

    // reponse à l'ajout d'url
    public void response(String urlToAdd) {

        // verifier la structure de urlToAdd
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
    }

}
