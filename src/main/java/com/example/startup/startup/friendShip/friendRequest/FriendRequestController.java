package com.example.startup.startup.friendShip.friendRequest;


import com.example.startup.startup.friendShip.friendRequest.dto.request.AddFriendRequest;
import com.example.startup.startup.model.SimpleResponseRest;
import com.example.startup.startup.springSecurity.CustomUserDetails;
import com.example.startup.startup.utils.MakingResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/friend-request")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    @Autowired
    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("/send")
    ResponseEntity<SimpleResponseRest> sendRequest(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody AddFriendRequest request
    ) {
        friendRequestService.sendRequest(userDetails,request);
        return MakingResponse.makingResponse(new SimpleResponseRest());
    }
}
