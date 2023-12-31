package com.example.startup.startup.userDetails.appUser.response;

import com.example.startup.startup.globalEnums.Gender;
import lombok.Data;

@Data
public class AppUserDetailsResponse {
        private String id;
        private String appUserId;
        private String fullName;
        private Gender gender;
        private String mobileNumber;
        private String email;
        private String address;
        private String profilePicture;
        private Boolean isActive;
}
