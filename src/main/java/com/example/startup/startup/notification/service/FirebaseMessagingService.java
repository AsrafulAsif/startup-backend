package com.example.startup.startup.notification.service;


import com.example.startup.startup.notification.dto.request.SingleUserNotificationRequest;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FirebaseMessagingService {
    public void sendNotificationByToken(SingleUserNotificationRequest request) {

        Notification notification = this.makingNotification(request.getNotificationTitle(), request.getNotificationBody(), request.getNotificationImageUrl());
        AndroidConfig androidConfig = this.makingAndroidConfig(request.getChannelId());
        Map<String, String> data = new HashMap<>();
        if (request.getType()!=null){
            data.put("type", request.getType());
        }

        Message message = Message.builder()
                .setToken(request.getFcmToken())
                .setNotification(notification)
                .putAllData(data)
                .setAndroidConfig(androidConfig)
                .build();
            FirebaseMessaging.getInstance(FirebaseApp.getInstance("StartUp")).sendAsync(message);
    }

    private Notification makingNotification(String title, String body,String image) {
        return Notification.builder().setTitle(title).setBody(body).setImage(image).build();
    }

    private AndroidConfig makingAndroidConfig(String channelId ){
        return AndroidConfig.builder().setNotification(AndroidNotification.builder().setChannelId(channelId).build()).build();
    }


}
