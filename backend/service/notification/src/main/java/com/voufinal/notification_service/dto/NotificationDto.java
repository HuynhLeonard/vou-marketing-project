package com.voufinal.notification_service.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationDto {
    private String id;
    private String title;
    private String description;
    private String imageUrl;
}
