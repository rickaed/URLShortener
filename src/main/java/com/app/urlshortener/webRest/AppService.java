package com.app.urlshortener.webRest;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;


@Service
public class AppService {

    public AppService() {
    }

   // verifie que l'url commence par http(s)
    final  boolean validUrl(String longUrl) throws URISyntaxException {
        
        URI incomingUrl = new URI(longUrl);
        if (incomingUrl.getScheme().equals("http") || incomingUrl.getScheme().equals("https")) {
            return true;
        } else {
            return false;
        }
    }

}
