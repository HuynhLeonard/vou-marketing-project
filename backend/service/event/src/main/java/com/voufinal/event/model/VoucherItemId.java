package com.voufinal.event.model;

import com.voufinal.event.entity.Voucher;
import com.voufinal.event.entity.Item;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class VoucherItemId implements Serializable {
    private Voucher voucher;

    private Item item;

    // equals() and hashCode()
}