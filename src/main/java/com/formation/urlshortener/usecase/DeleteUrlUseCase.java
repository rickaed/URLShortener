package com.formation.urlshortener.usecase;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.formation.urlshortener.bdd.BddRepository;
import com.formation.urlshortener.bdd.UrlEntity;
import com.formation.urlshortener.personalexception.InvalidTokenException;
import com.formation.urlshortener.personalexception.MissingUrlException;

@Service
public class DeleteUrlUseCase {
    BddRepository bddRepository;

    DeleteUrlUseCase(BddRepository bddRepository) {
        this.bddRepository = bddRepository;
    }

    public void checkToDelete(String id, String token) throws MissingUrlException, IOException, InvalidTokenException {
        System.out.println("@@@@@@@ Method checkToDelete");
        System.out.println("@@@@@@@ mon entity à delete : "+bddRepository.findById(id));
        if (bddRepository.idExist(id)) {
            System.out.println("@@@@@@@ Id existe ->");
            UrlEntity urlEntityToDelete = bddRepository.findById(id);
            if (urlEntityToDelete.getToken().equals(token)) {
                System.out.println("@@@@@@@ token valide");
                bddRepository.deleteUrl(urlEntityToDelete);
            } else {
                System.out.println("@@@@@@@ MAUVAIS TOKEN !!!");
                throw new InvalidTokenException();
            }
        }
    }

}
