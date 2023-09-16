package com.example.startup.startup.model.response;

import lombok.Data;

@Data
public class AppUserResponse {
    private Boolean status;
    private String authorizationToken;
}
