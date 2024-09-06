package com.voufinal.notification_service.service;

import com.voufinal.notification_service.dto.NotificationDto;

import java.util.List;

public interface INotificationsService {
    /**
     * Creates a new notification.
     *
     * @param notificationInfo
     * @return the id of the created notification
     */
    default String createNotification(NotificationDto notificationInfo) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Adds many users to a notification.
     *
     * @param notificationInfo
     * @param userIds
     * @return true if the users was added successfully, false otherwise
     */
    default String addUsersToNotification(NotificationDto notificationInfo, List<String> userIds) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
