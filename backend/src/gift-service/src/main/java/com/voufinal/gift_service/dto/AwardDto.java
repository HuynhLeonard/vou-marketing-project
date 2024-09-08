package com.voufinal.gift_service.dto;

import com.voufinal.gift_service.model.Item;
import com.voufinal.gift_service.model.ItemRepo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwardDto {
    Long idItemRepo;
    Long idPlayer;
    Long idItem;
    Long amount;
    String itemName;
    String imageUrl;

    public AwardDto(Item item, ItemRepo itemRepo) {
        this.idItemRepo = itemRepo.getIdItemRepo();
        this.idItem = item.getIdItem();
        this.idPlayer = itemRepo.getIdPlayer();
        this.amount = itemRepo.getAmount();
        this.itemName = item.getItemName();
        this.imageUrl = item.getImageUrl();
    }
}
