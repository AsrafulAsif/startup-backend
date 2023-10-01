package com.example.startup.startup.notification.dto.request;

import lombok.Data;

@Data
public class SingleUserNotificationRequest {
    private String fcmToken;
    private String notificationTitle;
    private String notificationBody;
    private String notificationImageUrl;
}
