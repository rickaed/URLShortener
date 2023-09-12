package com.app.urlshortener.bdd;

import org.springframework.stereotype.Service;


@Service
public class BddService {
   private final BddRepository bddRepository;

   public BddService(BddRepository bddRepository) {
      this.bddRepository = bddRepository;
   }
   
}
