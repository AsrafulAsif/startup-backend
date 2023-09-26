package com.example.startup.startup.auth.appUser;

import com.example.startup.startup.auth.appUser.entity.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<AppUser,String> {
    AppUser  findByUserNameAndMobileNumber(String userName,String mobileNumber);
    AppUser  findByMobileNumber(String mobileNumber);
}
