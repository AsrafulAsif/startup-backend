package com.example.startup.startup.auth.appUser.response;

import com.example.startup.startup.model.SimpleResponseRest;
import lombok.Builder;
import lombok.Data;

@Data
public class AppUserResponseRest extends SimpleResponseRest {
    private AppUserResponse authResponse;

    @Data
    public class AppUserResponse {
        private String userName;
        private Boolean status;
        private String authorizationToken;
    }
}
