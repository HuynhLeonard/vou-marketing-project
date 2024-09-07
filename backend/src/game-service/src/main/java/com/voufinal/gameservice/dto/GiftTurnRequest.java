package com.voufinal.gameservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftTurnRequest {
    String username;
    String email;
    Long receiverId;
    Long senderId;
    Long idGame;
    int turns;
}
