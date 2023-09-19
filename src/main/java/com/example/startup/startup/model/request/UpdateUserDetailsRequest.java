package com.example.startup.startup.model.request;

import com.example.startup.startup.model.enums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserDetailsRequest {
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
