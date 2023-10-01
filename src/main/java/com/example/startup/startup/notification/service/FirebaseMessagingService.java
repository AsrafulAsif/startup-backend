package com.example.startup.startup.notification.service;


import com.example.startup.startup.notification.dto.request.SingleUserNotificationRequest;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FirebaseMessagingService {
    public void sendNotificationByToken(SingleUserNotificationRequest request) {
        Map<String,String> data = new HashMap<>();
        data.put("name","asif");
        data.put("age","23");
        Message message = Message.builder()
                .setToken(request.getFcmToken())
                .setNotification(
                        makingNotification(
                                request.getNotificationTitle(),
                                request.getNotificationBody(),
                                request.getNotificationImageUrl()).build()
                ).putAllData(data)
                .build();
            FirebaseMessaging.getInstance(FirebaseApp.getInstance("StartUp")).sendAsync(message);
    }

    private Notification.Builder makingNotification(String title, String body, String image) {
        return Notification.builder().setTitle(title).setBody(body).setImage(image);

    }
}
