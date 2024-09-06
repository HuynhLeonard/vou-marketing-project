package com.voufinal.notification_service.dto;

import com.voufinal.notification_service.common.ActiveStatus;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationUserDto {
    private String notification_id;

    private String user_id;

    private boolean is_read;

    private ActiveStatus active_status;
}
