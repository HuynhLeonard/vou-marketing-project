package com.voufinal.mc_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AwardDto {
    Long idItemRepo;
    Long idPlayer;
    Long idItem;
    Long amount;
    String itemName;
    String imageUrl;
}
