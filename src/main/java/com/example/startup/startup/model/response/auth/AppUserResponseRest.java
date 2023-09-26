package com.example.startup.startup.model.response.auth;

import com.example.startup.startup.model.response.SimpleResponseRest;
import lombok.Data;

@Data
public class AppUserResponseRest extends SimpleResponseRest {
    private AppUserResponse authResponse;
}
