package com.example.startup.startup.entity;

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
    private String fullName;
    private String mobileNumber;
    private String appPassword;
    private String deviceId;
    private String fcmToken;
    private String deviceType;
    private Boolean status;
    private Date createdAt;
    private Date updatedAt;

}
