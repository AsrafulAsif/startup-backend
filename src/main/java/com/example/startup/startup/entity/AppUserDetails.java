package com.example.startup.startup.entity;

import com.example.startup.startup.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String email;
    private String address;
}
