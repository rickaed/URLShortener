package com.app.urlshortener.webRest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {
    
    @Value("${app.bdd.path}")
    private String bddPath;
    
    public String getBddPath() {
        return bddPath;
    }
}