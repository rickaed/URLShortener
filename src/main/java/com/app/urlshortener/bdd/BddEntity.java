package com.app.urlshortener.bdd;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BddEntity {

    private String id = UUID.randomUUID().toString();
    @JsonProperty("short-id")
    private String shortId;
    @JsonProperty("real-url")
    private String realUrl;
    @JsonProperty("removal-Token")
    private String token;
    @JsonProperty("access-date")
    private Long date;

    public BddEntity() {
    }

    public BddEntity(
            String id,
            String shortId,
            String realUrl,
            String token,
            Long date) {
        this.id = id;
        this.shortId = shortId;
        this.realUrl = realUrl;
        this.token = token;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

    public Long getDate() {
        return this.date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BddEntity{" +
                "short-id='" + shortId + '\'' +
                ", real-url='" + realUrl + '\'' +
                ", removal-Token='" + token + '\'' +
                ", access-date=" + date +
                ", id='" + id + '\'' +
                '}';
    }
}
