package com.example.startup.startup.userDetails.appUser.request;

import com.example.startup.startup.globalEnums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAppUserDetailsRequest {
    @NotNull(message = "Id can not be null")
    @NotEmpty(message ="Please provide Id.")
    private String id;
    private String fullName;
    private Gender gender;
    private String email;
    private String address;
    private Boolean isActive;
    private String profilePicture;
}
