package com.voufinal.notification_service.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTokenDto {
    private String email;
    private String token;
}
