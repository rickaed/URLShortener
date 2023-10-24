package com.formation.urlshortener.usecase;

import com.formation.urlshortener.bdd.BddRepository;
import com.formation.urlshortener.bdd.UrlEntity;
import com.formation.urlshortener.personalexception.InvalidTokenException;
import com.formation.urlshortener.personalexception.MissingUrlException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class DeleteUrlUseCase {
    private final BddRepository bddRepository;
    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");


    DeleteUrlUseCase(BddRepository bddRepository) {
        this.bddRepository = bddRepository;
    }

    public void checkToDelete(String id, String token) throws MissingUrlException, IOException, InvalidTokenException {
        System.out.println("@@@@@@@ Method checkToDelete");
        System.out.println("@@@@@@@ mon entity à delete : " + bddRepository.findById(id));
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


    public void autoDelete() throws IOException, ParseException {
        List<UrlEntity> list = bddRepository.readAllUrlEntities();
        for (UrlEntity url : list) {

            if (new Date().after(new Date((dateFormat.parse(url.getDate()).getTime() + (30l * 24 * 60 * 60 * 1000))))) {
                System.out.println("suppression de "+url.getRealUrl());
                bddRepository.deleteUrl(url);
            }
        }
    }


}
