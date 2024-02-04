package com.example.startup.startup.exception;

import lombok.NonNull;

public class JsonConversionException extends RuntimeException{

    public JsonConversionException(String message) {
        super((message));
    }
}
