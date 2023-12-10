package com.example.startup.startup.auth.appUser;

import com.example.startup.startup.auth.appUser.entity.AppUser;
import com.example.startup.startup.auth.appUser.response.AppUserListResponseRest;
import com.example.startup.startup.exception.BadRequestException;
import com.example.startup.startup.exception.UnAuthorizeException;
import com.example.startup.startup.auth.appUser.request.AppUserLoginRequest;
import com.example.startup.startup.auth.appUser.request.AppUserRegisterRequest;
import com.example.startup.startup.auth.appUser.response.AppUserResponseRest;
import com.example.startup.startup.springSecurity.service.JwtTokenService;
import com.example.startup.startup.utils.MakingPasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final JwtTokenService jwtTokenService;
    @Autowired
    public AppUserService(AppUserRepository appUserRepository, JwtTokenService jwtTokenService) {
        this.appUserRepository = appUserRepository;
        this.jwtTokenService = jwtTokenService;
    }

    public AppUserResponseRest registerAppUser(AppUserRegisterRequest request){
        AppUser appUser =  appUserRepository.findByMobileNumber(request.getPhoneNumber());
        if (appUser!=null) throw new BadRequestException("You already have an account.");

        appUser = AppUser.builder()
                .userName(request.getUserName())
                .mobileNumber(request.getPhoneNumber())
                .appPassword(MakingPasswordHash.makingPasswordHash(request.getAppPassword()))
                .fcmToken(request.getFcmToken())
                .deviceType(request.getDeviceType())
                .status(true)
                .createdAt(new Date(System.currentTimeMillis()))
                .build();
        appUserRepository.save(appUser);
        AppUserResponseRest response = new AppUserResponseRest();
        AppUserResponseRest.AppUserResponse appUserResponse = response. new AppUserResponse();
        appUserResponse.setUserName(appUser.getUserName());
        appUserResponse.setStatus(true);
        appUserResponse.setAuthorizationToken(jwtTokenService.generateJwtTokenWithInfo("User",appUser.getId(),appUser.getUserName(),appUser.getMobileNumber(),true));

        response.setAuthResponse(appUserResponse);
        return response;
    }


    public AppUserResponseRest logInAppUser(AppUserLoginRequest request){
        AppUser appUser =  appUserRepository.findByMobileNumber(request.getMobileNumber());
        if (appUser==null) throw new BadRequestException("You don't have an account.");
        if (!appUser.getStatus()) throw new UnAuthorizeException("You don't have any access.");
        AppUserResponseRest response = new AppUserResponseRest();
        if (BCrypt.checkpw(request.getAppPassword(),appUser.getAppPassword())){
            AppUserResponseRest.AppUserResponse appUserResponse = response.new AppUserResponse();
            appUserResponse.setUserName(appUser.getUserName());
            appUserResponse.setStatus(appUser.getStatus());
            appUserResponse.setAuthorizationToken(jwtTokenService.generateJwtTokenWithInfo("User",appUser.getId(),appUser.getUserName(),appUser.getMobileNumber(),appUser.getStatus()));
            response.setAuthResponse(appUserResponse);
            appUser.setDeviceType(request.getDeviceType());
            appUser.setFcmToken(request.getFcmToken());
            appUser.setLastLoginAt(new Date(System.currentTimeMillis()));
            appUserRepository.save(appUser);
        }else {
            throw new UnAuthorizeException("Password mismatch");
        }
        return response;
    }

    public AppUser findById(String appUserId){
        return appUserRepository.findById(appUserId).orElseThrow(()->new BadRequestException("User not found."));
    }


    public AppUserListResponseRest getAllAppUser(){
        List<AppUserListResponseRest.AppUserDto> appUserList = appUserRepository.findAllByOrderByCreatedAtDesc();
        AppUserListResponseRest response = new AppUserListResponseRest();
        response.setAppUserList(appUserList);
        return response;
    }
}
