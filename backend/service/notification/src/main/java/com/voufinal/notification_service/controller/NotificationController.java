package com.voufinal.notification_service.controller;

import com.voufinal.notification_service.service.NotificationsService;
import com.voufinal.notification_service.service.TokenService;

import com.voufinal.notification_service.dto.AddUsersRequestDto;
import com.voufinal.notification_service.dto.NotificationDto;
import com.voufinal.notification_service.model.NotificationEventCreatedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private NotificationsService notificationsService;

    @PostMapping("/register")
    public ResponseEntity<String> registerToken(@RequestParam String userId, @RequestParam String token) {
        tokenService.saveOrUpdateToken(userId, token);
        return ResponseEntity.ok("Token saved successfully.");
    }

    @PostMapping("/users")
    public String addUsersToNotification(@RequestBody AddUsersRequestDto addUsersRequestDto) {
        String notificationId = null;
        log.info("Adding users to notification: " + addUsersRequestDto.getNotification().getId());
        try {
            notificationId = notificationsService.addUsersToNotification(addUsersRequestDto.getNotification(), addUsersRequestDto.getUserIds());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return notificationId;
    }

    @PostMapping("/create")
    public String createNotification(@RequestBody NotificationDto notificationDto) {
        log.info("Creating notification: " + notificationDto.getId());
        String notificationId = null;
        try {
            notificationId = notificationsService.createNotification(notificationDto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return notificationId;
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationEventCreatedData notificationDto) {
        log.info("Sending notification: " + notificationDto.getNotificationInfo().getId());
        String notificationId = null;
        try {
            List<String> userIds = notificationDto.getUserIds();
            List<CompletableFuture<Void>> futures = new ArrayList<>();

            if (userIds == null || userIds.isEmpty()) {
                log.info("No users found for notification: " + notificationDto.getNotificationInfo().getId());
                return "No users found for notification: " + notificationDto.getNotificationInfo().getId();
            }

            for (String userId : userIds) {
                log.info("Processing notification for userId: " + userId);
                futures.add(notificationsService.processUserNotificationAsync(userId, notificationDto.getNotificationInfo()));
            }

            CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            allOf.get();

            notificationId = notificationDto.getNotificationInfo().getId();

            log.info("Notification sent to all users.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return notificationId;
    }
}
