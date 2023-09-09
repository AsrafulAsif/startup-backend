package com.example.startup.startup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {

    public NotFoundException(String statusMessage){
        super((HttpStatus.NOT_FOUND),statusMessage);
    }
}
