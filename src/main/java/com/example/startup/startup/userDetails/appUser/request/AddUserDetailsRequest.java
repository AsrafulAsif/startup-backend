package com.example.startup.startup.userDetails.appUser.request;

import com.example.startup.startup.globalEnums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddUserDetailsRequest {
    @NotNull(message = "Full name required.")
    @NotEmpty(message = "Full name can not be empty.")
    private String fullName;
    @NotNull(message = "Gender required.")
    private Gender gender;
    private String email;
    private String address;
    private String profilePicture;
}
