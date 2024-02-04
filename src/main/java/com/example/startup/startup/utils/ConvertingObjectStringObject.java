package com.example.startup.startup.utils;

import com.example.startup.startup.exception.JsonConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertingObjectStringObject {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String  convertObjectToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException(e.getMessage());
        }
    }

    public static <T> T  convertStringToObject(String data,Class<T> classType) {
        try {
            return objectMapper.readValue(data, classType);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException(e.getMessage());
        }
    }
}
