package com.voufinal.player_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
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
