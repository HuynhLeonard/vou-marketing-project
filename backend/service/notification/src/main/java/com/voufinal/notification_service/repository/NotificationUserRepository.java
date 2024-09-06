package com.voufinal.notification_service.repository;

import com.voufinal.notification_service.entity.NotificationUser;
import com.voufinal.notification_service.model.NotificationUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationUserRepository extends JpaRepository<NotificationUser, NotificationUserId>, NotificationUserRepositoryCustom {
    List<NotificationUser> findByUserId(String userId);

    List<NotificationUser> findByNotificationId(String notificationId);

    List<String> findUserIdsByNotificationId(String notificationId);

    NotificationUser findByUserIdAndNotificationId(String userId, String notificationId);
}
