package com.voufinal.event.repository;

import java.util.List;

import com.voufinal.event.entity.EventVoucher;

public interface EventVoucherRepositoryCustom {
    EventVoucher findByEventAndVoucher(String eventId, String voucherId);
    List<EventVoucher> findByEvent(String voucherId);
}