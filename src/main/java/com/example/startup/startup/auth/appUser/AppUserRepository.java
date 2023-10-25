package com.example.startup.startup.auth.appUser;

import com.example.startup.startup.auth.appUser.entity.AppUser;
import com.example.startup.startup.auth.appUser.response.AppUserListResponseRest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser,String> {
    AppUser  findByMobileNumber(String mobileNumber);
    List<AppUserListResponseRest.AppUserDto> findAllByOrderByCreatedAtDesc();
}
