package com.voufinal.notification_service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class FCMConfig {
    private static final Logger logger = Logger.getLogger(FCMConfig.class.getName());

    @PostConstruct
    public void initialize() {
        try {
            // logger.info("Initializing Firebase...");

            // InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("vou-notifications-system-048e86e9e460.json");

            // if (serviceAccount == null) {
            //     logger.info("Service account key file not found in classpath");
            //     throw new RuntimeException("Service account key file not found in classpath");
            // }

            // FirebaseOptions options = new FirebaseOptions.Builder()
            //         .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            //         .setDatabaseUrl("https://vou-notifications-system-default-rtdb.asia-southeast1.firebasedatabase.app")
            //         .build();
            logger.info("GOOGLE_APPLICATION_CREDENTIALS: " + System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
            FileInputStream serviceAccount = new FileInputStream(System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
            // D:/IT/SpringBoot/VOU/vou-backend/services/notifications/src/main/resources/vou-notifications-system-048e86e9e460.json
            logger.info("serviceAccount: " + serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://voufinal-default-rtdb.firebaseio.com/")
                    .build();

            // FirebaseApp.initializeApp(options);


            // Check if FirebaseApp is already initialized
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("FirebaseApp initialized successfully.");
            } else {
                logger.info("FirebaseApp already initialized.");
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error initializing Firebase: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}
