package com.app.urlshortener.usecase;

import com.app.urlshortener.bdd.BddRepository;
import com.app.urlshortener.bdd.UrlEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
@Service
public class CreateUrlUseCase {
BddRepository bddRepository;


CreateUrlUseCase(BddRepository bddRepository){
    this.bddRepository=bddRepository;
}


    public UrlEntity createUrlEntity(URI newUrl, List<UrlEntity> list) throws IOException {

        UrlEntity newEntity = new UrlEntity();
        newEntity.setId(UUID.randomUUID().toString());
        newEntity.setShortId(genString(8));
        newEntity.setRealUrl(newUrl);
        newEntity.setToken(genString(40));
        newEntity.setDate(new Date());

        bddRepository.addUrl(newEntity,list);
       
        return newEntity;
    }

    // generation d'une suite de caractere alpohanumerique aléatoire
    // d'une longueur donnée
    public static String genString(int size) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
