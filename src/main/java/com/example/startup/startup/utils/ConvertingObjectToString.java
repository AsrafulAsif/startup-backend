package com.example.startup.startup.utils;

import com.example.startup.startup.exception.JsonConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertingObjectToString {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String  convertObjectToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException(e.getOriginalMessage());
        }
    }
}
