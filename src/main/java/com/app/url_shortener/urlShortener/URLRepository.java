package com.app.url_shortener.urlShortener;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.url_shortener.bdd.URLEntity;

@Repository
public interface URLRepository extends MongoRepository<URLEntity, String> {
    public URLEntity findByShortenerUrl(String shortUrl);
    public Optional<URLEntity> findById(String Id);

}
