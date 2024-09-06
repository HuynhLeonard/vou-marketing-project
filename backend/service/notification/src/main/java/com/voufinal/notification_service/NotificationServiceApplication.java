package com.voufinal.notification_service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.voufinal.notification_service.config.FirebaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com.voufinal.notification_service")
@EnableJpaAuditing
@EnableConfigurationProperties(FirebaseProperties.class)
@EnableJpaRepositories(basePackages = "com.voufinal.notification_service.repository")
public class NotificationServiceApplication extends AbstractNotificationServiceApplication{

//	@Bean
//	FileInputStream serviceAccount =
//			new FileInputStream("./serviceAccountKey.json");
//
//	FirebaseOptions options = new FirebaseOptions.Builder()
//			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
//			.build();
//
//	FirebaseApp.initializeApp(options);

	 @Bean
	 FirebaseMessaging firebaseMessaging() throws IOException {
	 	GoogleCredentials credentials = GoogleCredentials.fromStream(new ClassPathResource("serviceAccountKey.json").getInputStream());

	 	FirebaseOptions firebaseOptions = FirebaseOptions.builder()
	 			.setCredentials(credentials)
	 			.build();

	 	FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "voufinal-notifications");

	 	return FirebaseMessaging.getInstance(app);
	 }

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}


}
