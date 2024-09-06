package com.voufinal.notification_service.entity;

import com.voufinal.notification_service.common.ActiveStatus;
import com.voufinal.notification_service.exception.Base;
import com.voufinal.notification_service.model.NotificationUserId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NotificationUser extends Base {
    @EmbeddedId
    private NotificationUserId id;

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    @Column(name = "active_status", nullable = false)
    private ActiveStatus activeStatus;
}
