package com.voufinal.notification_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class NotificationRelatedPairId implements Serializable {
    @Column(name = "notification_id", nullable = false)
    private String notificationId;

    @Column(name = "related_key", nullable = false)
    private String relatedKey;

    @Column(name = "related_id", nullable = false)
    private String relatedId;
}
