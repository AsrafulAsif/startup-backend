package com.example.startup.startup.userDetails.appUser.entity;

import com.example.startup.startup.springSecurity.CustomUserDetails;
import com.example.startup.startup.globalEnums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "app-user-details")
public class AppUserDetails {
    @Id
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
    private Date createdAt;
    private CustomUserDetails createdBy;
    private Date updatedAt;
    private CustomUserDetails updatedBy;
}
