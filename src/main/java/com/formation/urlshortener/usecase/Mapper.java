package com.formation.urlshortener.usecase;

import com.formation.urlshortener.bdd.UrlEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class Mapper {

    public NewEntityDto toDto(UrlEntity urlEntity) {
        String id = urlEntity.getId();
        String shortId = urlEntity.getShortId();
        URI realUrl = urlEntity.getRealUrl();
        System.out.println("@@@@@@@ dans mon mapper : " + new NewEntityDto(id, shortId, realUrl));

        return new NewEntityDto(id, shortId, realUrl);
    }

}
