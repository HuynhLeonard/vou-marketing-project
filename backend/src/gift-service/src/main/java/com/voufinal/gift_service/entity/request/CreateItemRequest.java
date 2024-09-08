package com.voufinal.gift_service.entity.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemRequest {
    String itemName;

    String description;

    String imageUrl;

    Long idEvent;
}
