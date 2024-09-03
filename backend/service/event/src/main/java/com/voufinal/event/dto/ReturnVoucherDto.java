package com.voufinal.event.dto;

import java.time.LocalDateTime;

import com.voufinal.event.common.VoucherStatus;
import com.voufinal.event.common.VoucherUnitValue;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReturnVoucherDto {
    private String id;
    
    private String brand_id;

    private String voucherCode;

    private String qrCode;

    private String image;

    private double value;

    private String description;

    private String expiredDate;

    private VoucherStatus status;

    private VoucherUnitValue unitValue;

    private int numberOfVoucher;
}
