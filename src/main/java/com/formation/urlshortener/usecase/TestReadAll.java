package com.formation.urlshortener.usecase;

import com.formation.urlshortener.bdd.BddRepository;
import com.formation.urlshortener.bdd.UrlEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TestReadAll {
   private final BddRepository bddRepository;

    TestReadAll(BddRepository bddRepository) {
        this.bddRepository = bddRepository;
    }

    public List<UrlEntity> readAll() throws IOException {
        return bddRepository.readAllUrlEntities();
    }
}
