package com.example.startup.startup.auth.appUser;

import com.example.startup.startup.auth.appUser.entity.AppUser;
import com.example.startup.startup.exception.BadRequestException;
import com.example.startup.startup.exception.UnAuthorizeException;
import com.example.startup.startup.auth.appUser.dto.request.AppUserLoginRequest;
import com.example.startup.startup.auth.appUser.dto.request.AppUserRegisterRequest;
import com.example.startup.startup.auth.appUser.dto.response.AppUserResponse;
import com.example.startup.startup.auth.appUser.dto.response.AppUserResponseRest;
import com.example.startup.startup.springSecurity.service.JwtTokenService;
import com.example.startup.startup.utils.MakingPasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    @Autowired
    public UserService(UserRepository userRepository, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
    }

    public AppUserResponseRest registerAppUser(AppUserRegisterRequest request){
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
        appUserResponse.setUserName(appUser.getUserName());
        appUserResponse.setStatus(true);
        appUserResponse.setAuthorizationToken(jwtTokenService.generateJwtTokenWithInfo("User",appUser.getId(),appUser.getUserName(),appUser.getMobileNumber(),true));

        AppUserResponseRest response = new AppUserResponseRest();
        response.setAuthResponse(appUserResponse);
        return response;
    }


    public AppUserResponseRest logInAppUser(AppUserLoginRequest request){
        AppUser appUser =  userRepository.findByMobileNumber(request.getMobileNumber());
        if (appUser==null) throw new BadRequestException("You don't have an account.");
        AppUserResponseRest response = new AppUserResponseRest();
        if (BCrypt.checkpw(request.getAppPassword(),appUser.getAppPassword())){
            AppUserResponse appUserResponse = new AppUserResponse();
            appUserResponse.setUserName(appUser.getUserName());
            appUserResponse.setStatus(appUser.getStatus());
            appUserResponse.setAuthorizationToken(jwtTokenService.generateJwtTokenWithInfo("User",appUser.getId(),appUser.getUserName(),appUser.getMobileNumber(),appUser.getStatus()));
            response.setAuthResponse(appUserResponse);
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
