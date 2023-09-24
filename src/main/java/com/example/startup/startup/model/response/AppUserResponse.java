package com.example.startup.startup.model.response;

import com.example.startup.startup.model.enums.Gender;
import lombok.Data;

@Data
public class AppUserResponse {
    private String userName;
    private Boolean status;
    private String authorizationToken;
}
