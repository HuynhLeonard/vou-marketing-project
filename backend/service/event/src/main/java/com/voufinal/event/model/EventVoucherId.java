package com.voufinal.event.model;

import com.voufinal.event.entity.Event;
import com.voufinal.event.entity.Voucher;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class EventVoucherId implements Serializable {
    private Event event;

    private Voucher voucher;

    // equals() and hashCode()
}