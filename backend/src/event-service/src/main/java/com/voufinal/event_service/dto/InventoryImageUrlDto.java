package com.voufinal.event_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryImageUrlDto {
    String qrImgUrl;
    String voucherImgUrl;
}
