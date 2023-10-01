package com.example.startup.startup.auth.appUser.entity;

import com.example.startup.startup.globalEnums.DeviceType;
import com.example.startup.startup.springSecurity.CustomUserDetails;
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
@Document(value = "app-user")
public class AppUser{
    @Id
    private String id;
    private String userName;
    private String mobileNumber;
    private String appPassword;
    private String fcmToken;
    private DeviceType deviceType;
    private Boolean status;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLoginAt;
    private CustomUserDetails updatedBy;
}
