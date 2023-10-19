package com.formation.urlshortener.usecase;

import com.formation.urlshortener.bdd.BddRepository;
import com.formation.urlshortener.bdd.UrlEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@Service
public class CreateUrlUseCase {
    BddRepository bddRepository;
    Mapper mapper;

    CreateUrlUseCase(BddRepository bddRepository, Mapper mapper) {
        this.bddRepository = bddRepository;
        this.mapper = mapper;
    }

    public  HashMap<String, Object> createUrlEntity(URI newUrl) throws IOException {
        System.out.println("@@@@@@@ ma fonction createUrlEntity");
        UrlEntity newEntity = new UrlEntity(UUID.randomUUID().toString(), genString(8), newUrl, genString(40),
                new Date());
        System.out.println("@@@@@@@ ma nouvelle entity " + newEntity);

        bddRepository.addUrl(newEntity);

        HashMap<String, Object> elemToSend = new HashMap<>();
        elemToSend.put("entity", entityDto(newEntity));
        elemToSend.put("token", newEntity.getToken());
        System.out.println("@@@@@@@ mon token envoyé : "+elemToSend.get("token"));

        return elemToSend;
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

    public NewEntityDto entityDto(UrlEntity newUrlEntity) {
        return mapper.toDto(newUrlEntity);

    }
}
