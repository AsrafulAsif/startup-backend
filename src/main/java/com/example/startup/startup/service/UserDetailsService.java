package com.example.startup.startup.service;


import com.example.startup.startup.entity.AppUser;
import com.example.startup.startup.entity.AppUserDetails;
import com.example.startup.startup.exception.BadRequestException;
import com.example.startup.startup.model.ClientInfo;
import com.example.startup.startup.model.request.AddUserDetailsRequest;
import com.example.startup.startup.model.request.UpdateUserDetailsRequest;
import com.example.startup.startup.model.response.AppUserDetailsResponse;
import com.example.startup.startup.model.response.AppUserDetailsResponseRest;
import com.example.startup.startup.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserDetailsService {
    private final UserService userService;
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsService(UserService userService, UserDetailsRepository userDetailsRepository) {
        this.userService = userService;
        this.userDetailsRepository = userDetailsRepository;
    }



    public void addUserDetails(AddUserDetailsRequest request, ClientInfo appUser){
        AppUser checkUser = userService.findById(appUser.id);
        AppUserDetails appUserDetails = userDetailsRepository.findByAppUserId(appUser.id);
        if (appUserDetails!=null) throw new BadRequestException("You already added your profile details.");
        appUserDetails = AppUserDetails.builder()
                .appUserId(checkUser.getId())
                .fullName(request.getFullName())
                .gender(request.getGender())
                .mobileNumber(checkUser.getMobileNumber())
                .email(request.getEmail())
                .address(request.getAddress())
                .profilePicture(request.getProfilePicture())
                .setUp(true)
                .isActive(true)
                .createdAt(new Date(System.currentTimeMillis()))
                .createdBy(appUser)
                .build();
        userDetailsRepository.save(appUserDetails);
    }

    public void updateUserDetails(UpdateUserDetailsRequest request, ClientInfo appUser){
        AppUserDetails appUserDetails = userDetailsRepository
                .findByIdAndAppUserId(request.getId(),appUser.id);

        if (appUserDetails == null) throw new BadRequestException("Profile details not found.");

        appUserDetails.setFullName(request.getFullName()==null ? appUserDetails.getFullName() : request.getFullName());
        appUserDetails.setGender(request.getGender()== null?appUserDetails.getGender():request.getGender());
        appUserDetails.setEmail(request.getEmail() == null?appUserDetails.getEmail(): request.getEmail());
        appUserDetails.setAddress(request.getAddress()==null? appUserDetails.getAddress() : request.getAddress());
        appUserDetails.setProfilePicture(request.getProfilePicture()==null? appUserDetails.getProfilePicture() : request.getProfilePicture());
        appUserDetails.setIsActive(request.getIsActive() == null?appUserDetails.getIsActive():request.getIsActive());
        appUserDetails.setUpdatedAt(new Date(System.currentTimeMillis()));
        appUserDetails.setUpdatedBy(appUser);
        userDetailsRepository.save(appUserDetails);
    }

    public AppUserDetailsResponseRest getUserDetails(ClientInfo appUser){
        AppUserDetails appUserDetails = userDetailsRepository.findByAppUserId(appUser.id);
        if (appUserDetails==null) throw new BadRequestException("Profile details not found.");
        AppUserDetailsResponse appUserDetailsResponse = new AppUserDetailsResponse(appUserDetails);
        AppUserDetailsResponseRest responseRest = new AppUserDetailsResponseRest();
        responseRest.setUserDetails(appUserDetailsResponse);
        return responseRest;
    }
}
