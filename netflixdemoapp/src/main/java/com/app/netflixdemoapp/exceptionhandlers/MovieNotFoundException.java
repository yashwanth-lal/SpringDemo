package com.app.netflixdemoapp.exceptionhandlers;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(String message){
        super(message);
    }

    public MovieNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public MovieNotFoundException(Throwable cause) {
        super(cause);
    }
}
