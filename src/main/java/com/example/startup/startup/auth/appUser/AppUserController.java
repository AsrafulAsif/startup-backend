package com.example.startup.startup.auth.appUser;

import com.example.startup.startup.auth.appUser.request.AppUserLoginRequest;
import com.example.startup.startup.auth.appUser.request.AppUserRegisterRequest;
import com.example.startup.startup.auth.appUser.response.AppUserResponseRest;
import com.example.startup.startup.model.SimpleResponseRest;
import com.example.startup.startup.utils.MakingResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    @Operation(summary = "No need any Bearer Token.")
    ResponseEntity<SimpleResponseRest> registerAppUser(
            @Valid @RequestBody AppUserRegisterRequest request
    ) {
        AppUserResponseRest response = appUserService.registerAppUser(request);
        return MakingResponse.makingResponse(response);
    }

    @PostMapping("/login")
    @Operation(summary = "No need any Bearer Token.")
    ResponseEntity<SimpleResponseRest> loginAppUser(
            @Valid @RequestBody AppUserLoginRequest request
    ) {
        AppUserResponseRest response = appUserService.logInAppUser(request);
        return MakingResponse.makingResponse(response);
    }

}
