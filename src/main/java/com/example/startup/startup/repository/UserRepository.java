package com.example.startup.startup.repository;

import com.example.startup.startup.entity.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<AppUser,String> {
    AppUser findByUserNameAndMobileNumber(String userName,String mobileNumber);
}
