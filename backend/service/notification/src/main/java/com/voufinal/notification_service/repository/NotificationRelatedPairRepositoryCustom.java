package com.voufinal.notification_service.repository;

import com.voufinal.notification_service.entity.NotificationRelatedPair;

import java.util.List;

public interface NotificationRelatedPairRepositoryCustom {
    public List<NotificationRelatedPair> findByNotificationId(String notificationId);
}
