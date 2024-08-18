package com.voufinal.player_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long userId;
    private String userName;
    private String password;
    private String avatar;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private String gender;
    private String facebookAccount;
}
