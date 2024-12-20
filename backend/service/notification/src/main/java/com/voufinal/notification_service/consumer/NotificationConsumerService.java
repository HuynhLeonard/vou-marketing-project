package com.voufinal.notification_service.consumer;

import com.voufinal.notification_service.entity.NotificationRelatedPair;
import com.voufinal.notification_service.model.NotificationEventCreatedData;
import com.voufinal.notification_service.model.NotificationInfo;
import com.voufinal.notification_service.repository.UserTokenRepository;
import com.voufinal.notification_service.service.FCMService;
import com.voufinal.notification_service.repository.NotificationUserRepository;
import com.voufinal.notification_service.repository.NotificationRelatedPairRepository;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class NotificationConsumerService {
    private final FCMService fcmService;
    private final NotificationUserRepository notificationUserRepository;
    private final UserTokenRepository userTokenRepository;
    private final NotificationRelatedPairRepository notificationRelatedPairRepository;

    private static final Logger logger = Logger.getLogger(NotificationConsumerService.class.getName());

    @KafkaListener(topics = "event-notification", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void listenEventNotification(ConsumerRecord<String, NotificationEventCreatedData> record, Acknowledgment acknowledgment) {
        try {
            NotificationEventCreatedData notificationDto = record.value();

            // List<String> userIds = notificationUserRepository.findUserIdsByNotificationId(notificationDto.getId());
            List<String> userIds = notificationDto.getUserIds();
            // Create a list to hold all CompletableFutures
            List<CompletableFuture<Void>> futures = new ArrayList<>();

            if (userIds == null || userIds.isEmpty()) {
                logger.info("No users found for notification: " + notificationDto.getNotificationInfo().getId());
                acknowledgment.acknowledge();
                return;
            }

            for (String userId : userIds) {
                logger.info("Processing notification for userId: " + userId);
                futures.add(processUserNotificationAsync(userId, notificationDto.getNotificationInfo()));
            }

            // Wait for all CompletableFutures to complete
            CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            allOf.get(); // This will block until all async tasks are complete

            // Acknowledge after all processing is complete
            acknowledgment.acknowledge();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
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
}
