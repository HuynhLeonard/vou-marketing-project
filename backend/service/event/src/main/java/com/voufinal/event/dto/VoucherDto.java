package com.voufinal.event.dto;

import com.voufinal.event.common.VoucherStatus;
import com.voufinal.event.common.VoucherUnitValue;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoucherDto {
    private String id;
    
    private String brand_id;

    private String voucherCode;

    private String qrCode;

    private String image;

    private double value;

    private String description;

    private LocalDateTime expiredDate;

    private VoucherStatus status;

    private VoucherUnitValue unitValue;
}