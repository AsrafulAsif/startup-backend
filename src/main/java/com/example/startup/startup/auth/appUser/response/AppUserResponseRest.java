package com.example.startup.startup.auth.appUser.response;

import com.example.startup.startup.model.SimpleResponseRest;
import lombok.Data;

@Data
public class AppUserResponseRest extends SimpleResponseRest {
    private AppUserResponse authResponse;
}