package com.example.startup.startup.model.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AppUserRegisterRequestRest {
    @NotNull(message = "User name required.")
    @NotEmpty(message = "User name can not be empty.")
    @Size(min = 4,message = "User name must be contains 4 character.")
    private String userName;
    @NotNull(message = "Full name required.")
    @NotEmpty(message = "Full name can not be empty.")
    private String fullName;
    @NotNull(message = "Mobile number required.")
    @NotEmpty(message = "Mobile number can not be empty.")
    @Size(min = 11,message = "Invalid phone number.")
    private String phoneNumber;
    @NotNull(message = "Password required.")
    @NotEmpty(message = "Password can not be empty.")
    @Size(min = 4,message = "Password must be contains 4 character.")
    private String appPassword;
    private String deviceId;
    @NotNull(message = "FCM token required.")
    @NotEmpty(message = "FCM token can not be empty.")
    private String fcmToken;
    private String deviceType;
}
