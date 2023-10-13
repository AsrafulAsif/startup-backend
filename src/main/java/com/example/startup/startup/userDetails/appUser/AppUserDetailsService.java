package com.example.startup.startup.userDetails.appUser;


import com.example.startup.startup.auth.appUser.AppUserService;
import com.example.startup.startup.springSecurity.CustomUserDetails;
import com.example.startup.startup.auth.appUser.entity.AppUser;
import com.example.startup.startup.userDetails.appUser.entity.AppUserDetails;
import com.example.startup.startup.exception.BadRequestException;
import com.example.startup.startup.userDetails.appUser.request.AddAppUserDetailsRequest;
import com.example.startup.startup.userDetails.appUser.request.UpdateAppUserDetailsRequest;
import com.example.startup.startup.userDetails.appUser.response.AppUserDetailsResponse;
import com.example.startup.startup.userDetails.appUser.response.AppUserDetailsResponseRest;
import com.example.startup.startup.utils.ConvertingClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AppUserDetailsService {
    private final AppUserService appUserService;
    private final AppUserDetailsRepository userDetailsRepository;

    public AppUserDetailsService(AppUserService appUserService, AppUserDetailsRepository userDetailsRepository) {
        this.appUserService = appUserService;
        this.userDetailsRepository = userDetailsRepository;
    }


    public void addUserDetails(AddAppUserDetailsRequest request, CustomUserDetails appUser) {
        AppUser checkUser = appUserService.findById(appUser.getId());
        AppUserDetails appUserDetails = userDetailsRepository.findByAppUserId(appUser.getId());
        if (appUserDetails != null) throw new BadRequestException("You already added your profile details.");
        appUserDetails = AppUserDetails.builder()
                .appUserId(checkUser.getId())
                .fullName(request.getFullName())
                .gender(request.getGender())
                .mobileNumber(checkUser.getMobileNumber())
                .email(request.getEmail())
                .address(request.getAddress())
                .profilePicture(request.getProfilePicture())
                .isActive(true)
                .createdAt(new Date(System.currentTimeMillis()))
                .createdBy(appUser)
                .build();
        userDetailsRepository.save(appUserDetails);
    }

    public void updateUserDetails(UpdateAppUserDetailsRequest request, CustomUserDetails appUser) {
        AppUserDetails appUserDetails = userDetailsRepository
                .findByIdAndAppUserId(request.getId(), appUser.getId());

        if (appUserDetails == null) throw new BadRequestException("Profile details not found.");

        appUserDetails.setFullName(request.getFullName() == null ? appUserDetails.getFullName() : request.getFullName());
        appUserDetails.setGender(request.getGender() == null ? appUserDetails.getGender() : request.getGender());
        appUserDetails.setEmail(request.getEmail() == null ? appUserDetails.getEmail() : request.getEmail());
        appUserDetails.setAddress(request.getAddress() == null ? appUserDetails.getAddress() : request.getAddress());
        appUserDetails.setProfilePicture(request.getProfilePicture() == null ? appUserDetails.getProfilePicture() : request.getProfilePicture());
        appUserDetails.setIsActive(request.getIsActive() == null ? appUserDetails.getIsActive() : request.getIsActive());
        appUserDetails.setUpdatedAt(new Date(System.currentTimeMillis()));
        appUserDetails.setUpdatedBy(appUser);
        userDetailsRepository.save(appUserDetails);
    }

    public AppUserDetailsResponseRest getUserDetails(CustomUserDetails appUser) {
        AppUserDetails appUserDetails = userDetailsRepository.findByAppUserId(appUser.getId());
        if (appUserDetails == null) throw new BadRequestException("Profile details not found.");
        AppUserDetailsResponse appUserDetailsResponse = ConvertingClass.convertClass(appUserDetails, AppUserDetailsResponse.class);
        AppUserDetailsResponseRest responseRest = new AppUserDetailsResponseRest();
        responseRest.setUserDetails(appUserDetailsResponse);
        return responseRest;
    }
}
