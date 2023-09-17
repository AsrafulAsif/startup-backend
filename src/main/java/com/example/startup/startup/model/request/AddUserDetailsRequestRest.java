package com.example.startup.startup.model.request;

import com.example.startup.startup.model.enums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddUserDetailsRequestRest {
    @NotNull(message = "Full name required.")
    @NotEmpty(message = "Full name can not be empty.")
    private String fullName;
    @NotNull(message = "Gender required.")
    @NotEmpty(message = "Gender can not be empty.")
    private Gender gender;
    private String email;
    private String address;
    private String profilePicture;
}
