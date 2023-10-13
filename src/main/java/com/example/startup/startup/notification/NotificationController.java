package com.example.startup.startup.notification;

import com.example.startup.startup.model.SimpleResponseRest;
import com.example.startup.startup.notification.dto.request.SingleUserNotificationRequest;
import com.example.startup.startup.notification.service.FirebaseMessagingService;
import com.example.startup.startup.utils.MakingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {
    private final FirebaseMessagingService firebaseMessagingService;

    public NotificationController(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @PostMapping("/single-user")
    ResponseEntity<SimpleResponseRest> registerAppUser(
            @RequestBody SingleUserNotificationRequest request
    ) {
        firebaseMessagingService.sendNotificationByToken(request);
        return MakingResponse.makingResponse(new SimpleResponseRest());
    }
}
