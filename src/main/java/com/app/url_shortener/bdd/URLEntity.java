package com.app.url_shortener.bdd;


import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collation = "urlshorter")
public class URLEntity {
    @Id
    private UUID id = UUID.randomUUID();;
    @JsonProperty("short-url")
    private String shortUrl;
    @JsonProperty("real-url")
    private String realUrl;
    @JsonProperty("Removal-Token")
    private String token;
    private String date;

    public URLEntity() {
    }

    public URLEntity(
            String shortUrl,
            String realUrl) {
        this.shortUrl = shortUrl;
        this.realUrl = realUrl;
    }

    @Override
    public String toString() {
        return String.format("URLEntity[id=%s,shortUrl=%s,realUrl=%s,token=%s,date=%s]",
                id, shortUrl, realUrl,token,date);

    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShortUrl() {
        return this.shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getRealUrl() {
        return this.realUrl;
    }

    public void setRealUrl(String realUrl) {
        this.realUrl = realUrl;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
