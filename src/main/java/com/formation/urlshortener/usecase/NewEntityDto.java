package com.formation.urlshortener.usecase;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewEntityDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("short-id")
    private String shortId;
    @JsonProperty("real-url")
    private URI realUrl;

    public NewEntityDto(String id, String shortId, URI realUrl) {
        this.id=id;
        this.shortId=shortId;
        this.realUrl=realUrl;
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

    public URI getRealUrl() {
        return this.realUrl;
    }

    public void setRealUrl(URI realUrl) {
        this.realUrl = realUrl;
    }
    @Override
    public String toString() {
        return "{" + "\n" +
                "short-id :'" + shortId + '\'' +
                ",\n real-url :'" + realUrl + '\'' +
                ",\n id :'" + id + '\'' + "\n" +
                '}';
    }
}
