package com.formation.urlshortener.bdd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public final class UrlEntity {
    @JsonProperty("id")
    private String id;
    @JsonProperty("short-id")
    private String shortId;
    @JsonProperty("real-url")
    private URI realUrl;
    @JsonProperty("X-Removal-Token")
    private String token;
    @JsonProperty("last-date-access")
    String date;

    public UrlEntity() {
    }

    @JsonCreator
    public UrlEntity(
            @JsonProperty("id") final String id,
            @JsonProperty("short-id") final String shortId,
            @JsonProperty("real-url") final URI realUrl,
            @JsonProperty("X-Removal-Token") final String token,
            @JsonProperty("last-date-access") String date) {

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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" + "\n" +
                "short-id :'" + shortId + '\'' +
                ",\n real-url :'" + realUrl + '\'' +
                ",\n X-Removal-Token :'" + token + '\'' +
                ",\n last-date-access :" + date +
                ",\n id :'" + id + '\'' + "\n" +
                '}';
    }

}
