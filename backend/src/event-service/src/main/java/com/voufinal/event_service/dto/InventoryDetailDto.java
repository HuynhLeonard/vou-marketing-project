package com.voufinal.event_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDetailDto {
    private String voucher_type;
    private String voucher_code;
    private String voucher_description;
    private String voucher_name;
    private Long voucher_price;
    private String qrCode;
    private String imageUrl;
    private Long aim_coin;
    private Timestamp expiration_date;
    private List<ItemDetailDto> items;
    private Long event_id;
}
