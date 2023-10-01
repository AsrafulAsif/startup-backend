package com.example.startup.startup.auth.appUser.request;

import com.example.startup.startup.globalEnums.DeviceType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AppUserRegisterRequest {
    @NotNull(message = "User name required.")
    @NotEmpty(message = "User name can not be empty.")
    @Size(min = 4,message = "User name must be contains 4 character.")
    private String userName;
    @NotNull(message = "Mobile number required.")
    @NotEmpty(message = "Mobile number can not be empty.")
    @Size(min = 11,message = "Invalid phone number.")
    private String phoneNumber;
    @NotNull(message = "Password required.")
    @NotEmpty(message = "Password can not be empty.")
    @Size(min = 4,message = "Password must be contains 4 character.")
    private String appPassword;
    @NotNull(message = "FCM token required.")
    @NotEmpty(message = "FCM token can not be empty.")
    private String fcmToken;
    private DeviceType deviceType;
}
