package com.example.startup.startup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class SimpleResponseRest {
    public String message;
    public int statusCode;
}
