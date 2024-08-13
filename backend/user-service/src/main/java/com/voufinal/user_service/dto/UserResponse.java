package com.voufinal.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String userName;
    private String userFullName;
    private String email;
    private String phoneNumber;
    private Boolean status;
}
