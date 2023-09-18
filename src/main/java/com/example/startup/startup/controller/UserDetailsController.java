package com.example.startup.startup.controller;


import com.example.startup.startup.model.ClientInfo;
import com.example.startup.startup.model.request.AddUserDetailsRequestRest;
import com.example.startup.startup.model.request.UpdateUserDetailsRequestRest;
import com.example.startup.startup.model.response.AppUserDetailsResponseRest;
import com.example.startup.startup.model.response.SimpleResponseRest;
import com.example.startup.startup.service.UserDetailsService;
import com.example.startup.startup.utils.MakingResponse;
import com.example.startup.startup.utils.MakingToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/set-up-user-details")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final MakingToken makingToken;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService, MakingToken makingToken) {
        this.userDetailsService = userDetailsService;
        this.makingToken = makingToken;
    }

    @GetMapping
    ResponseEntity<SimpleResponseRest> getProfile(
            @RequestHeader(value = "Authorization") String authorization
    ){
        ClientInfo appUser = makingToken.verifyTokenWithInfo(authorization);
        AppUserDetailsResponseRest response = userDetailsService.getUserDetails(appUser);
        return MakingResponse.makingResponse(response);
    }

    @PostMapping()
    ResponseEntity<SimpleResponseRest> addProfile(
            @RequestHeader(value = "Authorization") String authorization,
            @Valid @RequestBody AddUserDetailsRequestRest request
    ){
        ClientInfo appUser = makingToken.verifyTokenWithInfo(authorization);
        SimpleResponseRest response = new SimpleResponseRest();
        userDetailsService.addUserDetails(request,appUser);
        return MakingResponse.makingResponse(response);
    }

    @PutMapping()
    ResponseEntity<SimpleResponseRest> updateProfile(
            @RequestHeader(value = "Authorization") String authorization,
            @Valid @RequestBody UpdateUserDetailsRequestRest request
    ){
        ClientInfo appUser = makingToken.verifyTokenWithInfo(authorization);
        SimpleResponseRest response = new SimpleResponseRest();
        userDetailsService.updateUserDetails(request,appUser);
        return MakingResponse.makingResponse(response);
    }
}
