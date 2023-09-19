package com.example.startup.startup.controller;

import com.example.startup.startup.model.request.AppUserLoginRequest;
import com.example.startup.startup.model.request.AppUserRegisterRequest;
import com.example.startup.startup.model.response.AppUserResponseRest;
import com.example.startup.startup.model.response.SimpleResponseRest;
import com.example.startup.startup.service.UserService;
import com.example.startup.startup.utils.MakingResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<SimpleResponseRest> registerAppUser(
            @Valid @RequestBody AppUserRegisterRequest request
    ){
        AppUserResponseRest response = userService.registerAppUser(request);
        return MakingResponse.makingResponse(response);
    }

    @PostMapping("/login")
    ResponseEntity<SimpleResponseRest> loginAppUser(
            @Valid @RequestBody AppUserLoginRequest request
    ){
        AppUserResponseRest response = userService.logInAppUser(request);
        return MakingResponse.makingResponse(response);
    }

}
