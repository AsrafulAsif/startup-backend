package com.example.startup.startup.friend.entity;

import com.example.startup.startup.globalEnums.FriendRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "user-friend-request")
public class UserRequestFriend {
    @Id
    private String id;
    private String fromUserId;
    private String toUserId;
    private Date sendingRequestDate;
    private FriendRequestStatus status;
}
