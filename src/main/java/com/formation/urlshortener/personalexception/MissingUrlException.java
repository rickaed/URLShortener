package com.formation.urlshortener.personalexception;

public class MissingUrlException extends Exception {
    public MissingUrlException(String message) {
        super(message);
    }
}
