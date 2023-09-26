package com.example.startup.startup.userDetails.appUser;

import com.example.startup.startup.userDetails.appUser.entity.AppUserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsRepository extends MongoRepository<AppUserDetails,String> {
    AppUserDetails findByIdAndAppUserId(String id,String appUserId);
    AppUserDetails findByAppUserId(String appUserId);
}
