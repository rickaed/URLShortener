package com.app.urlshortener.webrest;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class AppService {

    public AppService() {
    }

    // verifie que l'url commence par http(s)
    final boolean validUrl(String longUrl) throws URISyntaxException {

        URI incomingUrl = new URI(longUrl);
        // if (incomingUrl.getScheme().equals("http") ||
        // incomingUrl.getScheme().equals("https")){
        // exist()
        // }
        // throw invalidUrlExeption()
        return incomingUrl.getScheme().equals("http") || incomingUrl.getScheme().equals("https");
    }

}
