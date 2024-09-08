package com.voufinal.gift_service.entity;

import com.voufinal.gift_service.model.Voucher;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserVoucher {
    private Voucher voucher;
    private Long amount;

    public UserVoucher(Voucher voucher, Long amount) {
        this.voucher = voucher;
        this.amount = amount;
    }
}
