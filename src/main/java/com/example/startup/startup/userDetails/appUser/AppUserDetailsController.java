package com.example.startup.startup.userDetails.appUser;


import com.example.startup.startup.springSecurity.CustomUserDetails;
import com.example.startup.startup.userDetails.appUser.request.AddAppUserDetailsRequest;
import com.example.startup.startup.userDetails.appUser.request.UpdateAppUserDetailsRequest;
import com.example.startup.startup.userDetails.appUser.response.AppUserDetailsResponseRest;
import com.example.startup.startup.model.SimpleResponseRest;
import com.example.startup.startup.utils.MakingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user-details")
public class AppUserDetailsController {

    private final AppUserDetailsService appUserDetailsService;

    public AppUserDetailsController(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @PreAuthorize("@permissionService.hasPermission()")
    @PostMapping()
    @Operation(summary = "Need Bearer Token." ,security = @SecurityRequirement(name = "Authorization"))
    ResponseEntity<SimpleResponseRest> addProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody AddAppUserDetailsRequest request
    ){
        SimpleResponseRest response = new SimpleResponseRest();
        appUserDetailsService.addUserDetails(request,userDetails);
        return MakingResponse.makingResponse(response);
    }

    @PreAuthorize("@permissionService.hasPermission()")
    @PutMapping()
    @Operation(summary = "Need Bearer Token." ,security = @SecurityRequirement(name = "Authorization"))
    ResponseEntity<SimpleResponseRest> updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateAppUserDetailsRequest request
    ){
        SimpleResponseRest response = new SimpleResponseRest();
        appUserDetailsService.updateUserDetails(request,userDetails);
        return MakingResponse.makingResponse(response);
    }

    @PreAuthorize("@permissionService.hasPermission()")
    @GetMapping
    @Operation(summary = "Need Bearer Token." ,security = @SecurityRequirement(name = "Authorization"))
    ResponseEntity<SimpleResponseRest> getProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        AppUserDetailsResponseRest response = appUserDetailsService.getUserDetails(userDetails);
        return MakingResponse.makingResponse(response);
    }
}
