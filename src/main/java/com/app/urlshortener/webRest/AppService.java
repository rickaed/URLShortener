package com.app.urlshortener.webRest;

import java.util.ArrayList;

import com.app.urlshortener.bdd.BddEntity;

public class AppService {
    private static ArrayList<BddEntity> bdd;
    private Config config;

    public AppService(Config config) {
        this.config = config;
    }

   

}
