package com.app.url_shortener.urlShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    private URLRepository urlRepository;

    

}
