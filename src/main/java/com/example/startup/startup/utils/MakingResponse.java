package com.example.startup.startup.utils;

import com.example.startup.startup.model.SimpleResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MakingResponse {
    public static void makingSuccessMessage(String message, SimpleResponseRest response){
        response.message = message;
        response.statusCode = 200;
    }
    public static ResponseEntity<SimpleResponseRest> makingResponse(SimpleResponseRest response){
        makingSuccessMessage("Successful",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
