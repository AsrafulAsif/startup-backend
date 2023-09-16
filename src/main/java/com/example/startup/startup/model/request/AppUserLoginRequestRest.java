package com.example.startup.startup.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppUserLoginRequestRest {
    @NotNull(message = "Mobile Number required.")
    @NotEmpty(message = "Mobile Number can not be empty.")
    private String mobileNumber;
    @NotNull(message = "Password required.")
    @NotEmpty(message = "Password can not be empty.")
    private String appPassword;
}
