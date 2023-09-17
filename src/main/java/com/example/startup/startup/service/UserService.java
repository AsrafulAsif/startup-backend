package com.example.startup.startup.service;

import com.example.startup.startup.entity.AppUser;
import com.example.startup.startup.exception.BadRequestException;
import com.example.startup.startup.exception.UnAuthorizeException;
import com.example.startup.startup.model.request.AppUserLoginRequestRest;
import com.example.startup.startup.model.request.AppUserRegisterRequestRest;
import com.example.startup.startup.model.response.AppUserResponse;
import com.example.startup.startup.model.response.AppUserResponseRest;
import com.example.startup.startup.repository.UserRepository;
import com.example.startup.startup.utils.MakingPasswordHash;
import com.example.startup.startup.utils.MakingToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final MakingToken makingToken;
    @Autowired
    public UserService(UserRepository userRepository, MakingToken makingToken) {
        this.userRepository = userRepository;
        this.makingToken = makingToken;
    }

    public AppUserResponseRest registerAppUser(AppUserRegisterRequestRest request){
        AppUser appUser =  userRepository.findByUserNameAndMobileNumber(request.getUserName(),request.getPhoneNumber());
        if (appUser!=null) throw new BadRequestException("You already have an account.");

        appUser = AppUser.builder()
                .userName(request.getUserName())
                .mobileNumber(request.getPhoneNumber())
                .appPassword(MakingPasswordHash.makingPasswordHash(request.getAppPassword()))
                .deviceId(request.getDeviceId())
                .fcmToken(request.getFcmToken())
                .deviceType(request.getDeviceType())
                .status(true)
                .createdAt(new Date(System.currentTimeMillis()))
                .build();
        userRepository.save(appUser);
        AppUserResponse appUserResponse = new AppUserResponse();
        appUserResponse.setStatus(true);
        appUserResponse.setAuthorizationToken(makingToken.generateJwtTokenWithInfo("User",appUser.getId(),appUser.getUserName(),appUser.getMobileNumber(),true));

        AppUserResponseRest response = new AppUserResponseRest();
        response.setResponse(appUserResponse);
        return response;
    }


    public AppUserResponseRest logInAppUser(AppUserLoginRequestRest request){
        AppUser appUser =  userRepository.findByMobileNumber(request.getMobileNumber());
        if (appUser==null) throw new BadRequestException("You don't have an account.");
        AppUserResponseRest response = new AppUserResponseRest();
        if (BCrypt.checkpw(request.getAppPassword(),appUser.getAppPassword())){
            AppUserResponse appUserResponse = new AppUserResponse();
            appUserResponse.setStatus(appUser.getStatus());
            appUserResponse.setAuthorizationToken(makingToken.generateJwtTokenWithInfo("User",appUser.getId(),appUser.getUserName(),appUser.getMobileNumber(),appUser.getStatus()));
            response.setResponse(appUserResponse);
            appUser.setLastLoginAt(new Date(System.currentTimeMillis()));
            userRepository.save(appUser);
        }else {
            throw new UnAuthorizeException("Password mismatch");
        }
        return response;
    }

    public AppUser findById(String appUserId){
        return userRepository.findById(appUserId).orElseThrow(()->new BadRequestException("User not found."));
    }
}
