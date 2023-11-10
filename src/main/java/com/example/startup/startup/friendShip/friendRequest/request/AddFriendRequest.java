package com.example.startup.startup.friendShip.friendRequest.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddFriendRequest {
    @NotNull(message = " UserId required.")
    @NotEmpty(message = "UserId can not be empty.")
    private String toUserId;
}
