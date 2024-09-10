package com.voufinal.statistic_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizWinnerMetadata {
    private Long idUser;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
    private String address;
    private String avatarUrl;
    private Integer rank;
}