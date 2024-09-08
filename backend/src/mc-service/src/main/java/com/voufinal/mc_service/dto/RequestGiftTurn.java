package com.voufinal.mc_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestGiftTurn {
    String username;
    String email;
    Long receiverId;
    Long senderId;
    Long idGame;
    int turns;
}
