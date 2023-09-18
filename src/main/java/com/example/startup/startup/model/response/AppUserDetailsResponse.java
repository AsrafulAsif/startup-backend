package com.example.startup.startup.model.response;

import com.example.startup.startup.entity.AppUserDetails;
import com.example.startup.startup.model.enums.Gender;
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
        private Boolean setUp;
        private Boolean isActive;

        public AppUserDetailsResponse(AppUserDetails appUserDetails){
                this.id=appUserDetails.getId();
                this.appUserId=appUserDetails.getAppUserId();
                this.fullName=appUserDetails.getFullName();
                this.gender=appUserDetails.getGender();
                this.mobileNumber=appUserDetails.getMobileNumber();
                this.email=appUserDetails.getEmail();
                this.address=appUserDetails.getAddress();
                this.profilePicture=appUserDetails.getProfilePicture();
                this.setUp=appUserDetails.getSetUp();
                this.isActive=appUserDetails.getIsActive();
        }
}
