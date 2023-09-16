package com.example.startup.startup.exception;

public class UnAuthorizeException extends RuntimeException {
    public int statusCode;
    public UnAuthorizeException(String message){
        super((message));
        this.statusCode=401;
    }
}
