package com.voufinal.gift_service.entity.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftItemRequest {
    private String senderUsername;
    private String receiverUsername;
    private String senderEmail;
    private String receiverEmail;
    private Long receiverId;
    private Long senderId;
    private Long itemId;
    private Long amount;
}
