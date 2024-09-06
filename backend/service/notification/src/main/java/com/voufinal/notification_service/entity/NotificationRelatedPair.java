package com.voufinal.notification_service.entity;

import com.voufinal.notification_service.common.ActiveStatus;
import com.voufinal.notification_service.exception.Base;
import com.voufinal.notification_service.model.NotificationRelatedPairId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NotificationRelatedPair extends Base {
    @EmbeddedId
    private NotificationRelatedPairId id;

    @Column(name = "active_status", nullable = false)
    private ActiveStatus activeStatus;
}
