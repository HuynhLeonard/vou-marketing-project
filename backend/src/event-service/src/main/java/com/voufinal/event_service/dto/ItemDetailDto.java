package com.voufinal.event_service.dto;

import lombok.Data;

@Data
public class ItemDetailDto {
    private Long idItem;
    private String description;
    private Long idEvent;
    private String imageUrl;
    private String itemName;
}
