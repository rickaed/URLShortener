package com.app.urlshortener.personalexception;

public class InvalidUrlException extends Exception {
    public InvalidUrlException(String message){
        super(message);
    }
    
}
