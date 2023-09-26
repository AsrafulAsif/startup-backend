package com.example.startup.startup.userDetails.appUser.dto.response;

import com.example.startup.startup.model.SimpleResponseRest;
import lombok.Data;

@Data
public class AppUserDetailsResponseRest extends SimpleResponseRest {
    private AppUserDetailsResponse userDetails;
}
