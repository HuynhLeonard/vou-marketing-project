package com.voufinal.gift_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class InventoryImageDto {
    MultipartFile qrImg;
    MultipartFile voucherImg;
}
