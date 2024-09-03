package com.voufinal.event.dto;

import com.voufinal.event.common.EventIntermediateTableStatus;

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
public class EventVoucherDto {
    private EventDto event;

    private VoucherDto voucher;

    private int numberOfVoucher;

    private EventIntermediateTableStatus activeStatus;
}