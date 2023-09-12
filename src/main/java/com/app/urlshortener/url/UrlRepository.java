package com.app.urlshortener.url;


import org.springframework.stereotype.Repository;


@Repository
public class UrlRepository{
    private final URLEntity urlEntity;

   public UrlRepository(URLEntity urlEntity) {
      this.urlEntity = urlEntity;
   }
   

}