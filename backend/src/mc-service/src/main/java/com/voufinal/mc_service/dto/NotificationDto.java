package com.voufinal.mc_service.dto;

import lombok.Data;

@Data
public class NotificationDto {
    private Long idEvent;
    private String imageUrl;
    private String message;
    private Integer numDay;
}
