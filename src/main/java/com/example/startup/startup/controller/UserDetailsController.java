package com.example.startup.startup.controller;


import com.example.startup.startup.config.springSecurity.CustomUserDetails;
import com.example.startup.startup.model.request.AddUserDetailsRequest;
import com.example.startup.startup.model.request.UpdateUserDetailsRequest;
import com.example.startup.startup.model.response.AppUserDetailsResponseRest;
import com.example.startup.startup.model.response.SimpleResponseRest;
import com.example.startup.startup.service.UserDetailsService;
import com.example.startup.startup.utils.MakingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user-details")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;


    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    @Operation(summary = "Need Bearer Token." ,security = @SecurityRequirement(name = "Authorization"))
    ResponseEntity<SimpleResponseRest> getProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){
        AppUserDetailsResponseRest response = userDetailsService.getUserDetails(userDetails);
        return MakingResponse.makingResponse(response);
    }

    @PostMapping()
    ResponseEntity<SimpleResponseRest> addProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody AddUserDetailsRequest request
    ){
        SimpleResponseRest response = new SimpleResponseRest();
        userDetailsService.addUserDetails(request,userDetails);
        return MakingResponse.makingResponse(response);
    }

    @PutMapping()
    ResponseEntity<SimpleResponseRest> updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateUserDetailsRequest request
    ){
        SimpleResponseRest response = new SimpleResponseRest();
        userDetailsService.updateUserDetails(request,userDetails);
        return MakingResponse.makingResponse(response);
    }
}
