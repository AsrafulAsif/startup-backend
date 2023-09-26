package com.example.startup.startup.auth.appUser.dto.response;

import lombok.Data;

@Data
public class AppUserResponse {
    private String userName;
    private Boolean status;
    private String authorizationToken;
}
