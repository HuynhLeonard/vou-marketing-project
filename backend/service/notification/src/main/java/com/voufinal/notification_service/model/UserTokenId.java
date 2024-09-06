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
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserTokenId implements Serializable {
    @Column(name = "email", nullable = false)
    private String userId;

    @Column(name = "token", nullable = false)
    private String token;
}
