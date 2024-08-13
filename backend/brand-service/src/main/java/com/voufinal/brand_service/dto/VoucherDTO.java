package com.voufinal.brand_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VoucherDTO {
    private String id;
    private int value;
    private String description;
    private Date endDate;
}