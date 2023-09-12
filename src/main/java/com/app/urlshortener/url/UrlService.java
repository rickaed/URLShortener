package com.app.urlshortener.url;

import org.springframework.stereotype.Service;

@Service
public class UrlService {
   private final UrlRepository urlRepository;

   public UrlService(UrlRepository urlRepository) {
      this.urlRepository = urlRepository;
   }
   
 // verifier validité url
 /**  URI uri = URI.create(url);
        try {
            String uriScheme = uri.getScheme();
            if (uriScheme.equals("http")) {
                System.out.println("http");
            } else if (uriScheme.equals("https")) {
                System.out.println("https");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
  * 
  */

  
}
