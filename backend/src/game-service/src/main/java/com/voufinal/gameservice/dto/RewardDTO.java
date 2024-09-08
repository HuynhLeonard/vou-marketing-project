package com.voufinal.gameservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardDTO {
    Long idItemRepo;
    Long idPlayer;
    Long idItem;
    Long amount;
    String itemName;
    String imageUrl;
}