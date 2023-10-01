package com.example.startup.startup.friendShip.friendRequest.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "user-friend")
public class UserFriend {
    @Id
    private String id;
    private String appUserId;
    private List<String> friendsId;
}
