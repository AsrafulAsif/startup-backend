package com.example.startup.startup.auth.appUser.response;

import com.example.startup.startup.globalEnums.DeviceType;
import com.example.startup.startup.model.SimpleResponseRest;
import com.example.startup.startup.springSecurity.CustomUserDetails;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppUserListResponseRest extends SimpleResponseRest {
    private List<AppUserDto> appUserList;
    @Data
    @AllArgsConstructor
    public class AppUserDto {
        private String id;
        private String userName;
        private String mobileNumber;
        private DeviceType deviceType;
        private Boolean status;
        private Date createdAt;
        private Date updatedAt;
        private Date lastLoginAt;
        private CustomUserDetails updatedBy;
    }
}
