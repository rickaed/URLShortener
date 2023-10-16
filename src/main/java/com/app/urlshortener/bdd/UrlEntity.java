package com.app.urlshortener.bdd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.util.Date;

public final class UrlEntity {

    private String id;
    @JsonProperty("short-id")
    private String shortId;
    @JsonProperty("real-url")
    private URI realUrl;
    @JsonProperty("removal-Token")
    private String token;
    @JsonProperty("access-date")
    private Date date;

    public UrlEntity(){}

    public UrlEntity(
            final String id,
            final String shortId,
            final URI realUrl,
            final String token,
            Date date) {
        this.id = id;
        this.shortId = shortId;
        this.realUrl = realUrl;
        this.token = token;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getShortId() {
        return this.shortId;
    }

    public void setShortId(final String shortId) {
        this.shortId = shortId;
    }

    public URI getRealUrl() {
        return this.realUrl;
    }

    public void setRealUrl(final URI newUrl) {
        this.realUrl = newUrl;
    }

    public String getToken() {
        return this.token;
    }

     public void setToken(final String token) {
        this.token = token;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public  String toString() {
        return "{" + "\n" +
                "short-id :'" + shortId + '\'' +
                ",\n real-url :'" + realUrl + '\'' +
                ",\n removal-Token :'" + token + '\'' +
                ",\n access-date :" + date +
                ",\n id :'" + id + '\'' + "\n" +
                '}';
    }

}
