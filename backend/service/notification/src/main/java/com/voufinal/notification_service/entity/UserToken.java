package com.voufinal.notification_service.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserToken {
    @Id
    @DocumentId
    private String documentId;  // Firestore document ID

    private String userId;
    private String token;

    @ServerTimestamp
    private Date updatedAt;
}
