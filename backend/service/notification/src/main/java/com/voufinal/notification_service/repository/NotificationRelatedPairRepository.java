package com.voufinal.notification_service.repository;

import com.voufinal.notification_service.entity.NotificationRelatedPair;
import com.voufinal.notification_service.model.NotificationRelatedPairId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRelatedPairRepository extends JpaRepository<NotificationRelatedPair, NotificationRelatedPairId>, NotificationRelatedPairRepositoryCustom {
    List<NotificationRelatedPair> findByNotificationId(String notificationId);
}
