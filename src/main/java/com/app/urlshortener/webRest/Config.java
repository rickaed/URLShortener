package com.app.urlshortener.webRest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

    @Value("${bddPath}")
    private String bddPath;
    @Value("${tokenSecret}")
    private String secretCode;
    public String getBddPath() {
        return bddPath;
    }



    public String getSecretCode() {
        return this.secretCode;
    }

}