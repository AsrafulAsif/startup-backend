package com.example.startup.startup.auth.appUser.request;

import com.example.startup.startup.globalEnums.DeviceType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppUserLoginRequest {
    @NotNull(message = "Mobile Number required.")
    @NotEmpty(message = "Mobile Number can not be empty.")
    private String mobileNumber;
    @NotNull(message = "Password required.")
    @NotEmpty(message = "Password can not be empty.")
    private String appPassword;
    private DeviceType deviceType;
    private String fcmToken;
}
