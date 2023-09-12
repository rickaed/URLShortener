package com.app.urlshortener.url;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class URLEntity {
   
    private UUID id = UUID.randomUUID();;
    @JsonProperty("short-id")
    private String shortId;
    @JsonProperty("real-url")
    private String realUrl;
    @JsonProperty("removal-Token")
    private String token;
    private String date;

    public URLEntity() {
    }

    public URLEntity(
            String shortId,
            String realUrl) {
        this.shortId = shortId;
        this.realUrl = realUrl;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShortUrl() {
        return this.shortId;
    }

    public void setShortUrl(String shortId) {
        this.shortId = shortId;
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
