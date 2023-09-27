package com.example.startup.startup.userDetails.appUser;


import com.example.startup.startup.springSecurity.CustomUserDetails;
import com.example.startup.startup.userDetails.appUser.dto.request.AddUserDetailsRequest;
import com.example.startup.startup.userDetails.appUser.dto.request.UpdateUserDetailsRequest;
import com.example.startup.startup.userDetails.appUser.dto.response.AppUserDetailsResponseRest;
import com.example.startup.startup.model.SimpleResponseRest;
import com.example.startup.startup.utils.MakingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("@permissionService.hasPermission()")
    @PostMapping()
    @Operation(summary = "Need Bearer Token." ,security = @SecurityRequirement(name = "Authorization"))
    ResponseEntity<SimpleResponseRest> addProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody AddUserDetailsRequest request
    ){
        SimpleResponseRest response = new SimpleResponseRest();
        userDetailsService.addUserDetails(request,userDetails);
        return MakingResponse.makingResponse(response);
    }

    @PreAuthorize("@permissionService.hasPermission()")
    @PutMapping()
    @Operation(summary = "Need Bearer Token." ,security = @SecurityRequirement(name = "Authorization"))
    ResponseEntity<SimpleResponseRest> updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateUserDetailsRequest request
    ){
        SimpleResponseRest response = new SimpleResponseRest();
        userDetailsService.updateUserDetails(request,userDetails);
        return MakingResponse.makingResponse(response);
    }

    @PreAuthorize("@permissionService.hasPermission()")
    @GetMapping
    @Operation(summary = "Need Bearer Token." ,security = @SecurityRequirement(name = "Authorization"))
    ResponseEntity<SimpleResponseRest> getProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        AppUserDetailsResponseRest response = userDetailsService.getUserDetails(userDetails);
        return MakingResponse.makingResponse(response);
    }
}
