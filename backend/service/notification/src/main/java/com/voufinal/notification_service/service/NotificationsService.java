package com.voufinal.notification_service.service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.voufinal.notification_service.common.ActiveStatus;
import com.voufinal.notification_service.config.FirebaseConfig;
import com.voufinal.notification_service.dto.NotificationDto;
import com.voufinal.notification_service.entity.NotificationRelatedPair;
import com.voufinal.notification_service.model.NotificationInfo;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.voufinal.notification_service.repository.NotificationRelatedPairRepository;
import com.voufinal.notification_service.repository.NotificationRepository;
import com.voufinal.notification_service.repository.NotificationUserRepository;
import com.voufinal.notification_service.repository.UserTokenRepository;
import com.voufinal.notification_service.consumer.NotificationConsumerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class NotificationsService implements INotificationsService{
    private final FCMService                    fcmService;
    private final NotificationRepository        notificationRepository;
    private final NotificationUserRepository    notificationUserRepository;
    private final NotificationRelatedPairRepository notificationRelatedPairRepository;
    private final UserTokenRepository           userTokenRepository;
    private final FirebaseConfig firebaseConfig;

    private final Firestore firestore;

    private static final Logger logger = Logger.getLogger(NotificationConsumerService.class.getName());

    @Override
    public String createNotification(NotificationDto notificationInfo) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            // ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("notifications").document(notificationInfo.getId()).set(notificationInfo);
            // Save the notification with the id as the document ID
            DocumentReference docRef = dbFirestore.collection("notifications").document(notificationInfo.getId());
            docRef.set(notificationInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return notificationInfo.getId();
    }

    @Override
    public String addUsersToNotification(NotificationDto notificationInfo, List<String> userIds) {

        String notificationId = null;

        try {
            notificationId = this.createNotification(notificationInfo);

            for (String userId : userIds) {
                saveUserNotificationToFirestore(notificationId, userId);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return notificationId;
    }

    @Async
    public CompletableFuture<Void> processUserNotificationAsync(String userId, NotificationInfo notificationDto) {
        return CompletableFuture.runAsync(() -> {
            try {
                List<NotificationRelatedPair> relatedPairs = notificationRelatedPairRepository.findByNotificationId(notificationDto.getId());
                String token = null;
                token = userTokenRepository.findTokenByUserId(userId);

                if (token != null) {
                    Map<String, String> data = new HashMap<>();
                    for (NotificationRelatedPair relatedPair : relatedPairs) {
                        data.put(relatedPair.getId().getRelatedKey(), relatedPair.getId().getRelatedId());
                    }

                    fcmService.sendNotification(token, notificationDto, data);
                    logger.info("Notification sent to userId: " + userId);
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error processing notification for userId: " + userId, e);
            }
        });
    }

    private void saveUserNotificationToFirestore(String notificationId, String userId) {

        try {
            // Define the Firestore collection for notification users
            CollectionReference notificationUsers = firestore.collection("notifications_users");

            Map<String, Object> userNotificationData = new HashMap<>();
            userNotificationData.put("user_id", userId);
            userNotificationData.put("notification_id", notificationId);
            userNotificationData.put("is_read ", false);
            userNotificationData.put("active_status", ActiveStatus.ACTIVE);

            notificationUsers.document(notificationId).set(userNotificationData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
