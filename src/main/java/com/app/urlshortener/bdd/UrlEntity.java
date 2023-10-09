package com.app.urlshortener.bdd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

//@
public class UrlEntity {

    private String id;
    @JsonProperty("short-id")
    private String shortId;
    @JsonProperty("real-url")
    private String realUrl;
    @JsonProperty("removal-Token")
    private String token;
    @JsonProperty("access-date")
    private Date date;

    public UrlEntity() {
    }

    public UrlEntity(
            String id,
            String shortId,
            String realUrl,
            String token,
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

    public void setId(String id) {
        this.id = id;
    }

    public String getShortId() {
        return this.shortId;
    }

    public void setShortId(String shortId) {
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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BddEntity{" +"\n"+
                "short-id :'" + shortId + '\'' +
                ",\n real-url :'" + realUrl + '\'' +
                ",\n removal-Token :'" + token + '\'' +
                ",\n access-date :" + date +
                ",\n id :'" + id + '\'' +"\n"+
                '}';
    }
}
