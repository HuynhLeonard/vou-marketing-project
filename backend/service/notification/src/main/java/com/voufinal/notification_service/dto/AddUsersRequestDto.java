package com.voufinal.notification_service.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddUsersRequestDto {
    private NotificationDto notification;
    private List<String> userIds;
}
