package com.example.startup.startup.globalConfig;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    @Value("${app.firebase-storage-bucket}")
    private String firebaseStorageBucket;


    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream());
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .setStorageBucket(firebaseStorageBucket)
                .build();
        if(FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options,"StartUp");
        }else {
            return FirebaseApp.getInstance("StartUp");
        }
    }
}
