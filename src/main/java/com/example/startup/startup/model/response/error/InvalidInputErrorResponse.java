package com.example.startup.startup.model.response.error;



import java.util.Map;

public class InvalidInputErrorResponse {
    public String message;
    public Integer statusCode;
    public Map<String,String> errors;
    public InvalidInputErrorResponse(String error, Integer statusCode, Map<String,String> errors) {
        this.message = error;
        this.statusCode = statusCode;
        this.errors = errors;
    }
}
