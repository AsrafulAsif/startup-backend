package com.example.startup.startup.friendShip.friendRequest;

import com.example.startup.startup.friendShip.friendRequest.entity.UserFriendRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends MongoRepository<UserFriendRequest,String> {
}
