package com.voufinal.gameservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlayerDTO {
    private Long idUser;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDateTime lockedDate;
    private String role;
    private String status;
    private String address;
    private String avatarUrl;
}
