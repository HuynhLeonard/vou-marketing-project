package com.voufinal.gift_service.dto;

import com.voufinal.gift_service.model.Item;
import lombok.Data;

@Data
public class ItemDetailDto {
    private Long idItem;
    private String description;
    private Long idEvent;
    private String imageUrl;
    private String itemName;

    public ItemDetailDto(Item item) {
        this.idItem = item.getIdItem();
        this.description = item.getDescription();
        this.idEvent = item.getIdEvent();
        this.imageUrl = item.getImageUrl();
        this.itemName = item.getItemName();
    }
}