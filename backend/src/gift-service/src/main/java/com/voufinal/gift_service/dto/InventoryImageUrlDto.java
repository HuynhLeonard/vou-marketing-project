package com.voufinal.gift_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryImageUrlDto {
    String qrImgUrl;
    String voucherImgUrl;

    public InventoryImageUrlDto(String qrImgUrl, String voucherImgUrl) {
        this.qrImgUrl = qrImgUrl;
        this.voucherImgUrl = voucherImgUrl;
    }
}
