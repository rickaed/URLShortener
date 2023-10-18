package com.formation.urlshortener.webrest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

    @Value("${bddPath}")
    private String bddPath;

    public String getBddPath() {
        return bddPath;
    }

}