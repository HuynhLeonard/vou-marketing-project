package com.voufinal.event.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.voufinal.event.repository.VoucherItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoucherItemService implements IVoucherItemService {

    private final VoucherItemRepository voucherItemRepository;

    @Override
    public Map<String, Integer> getItemsQuantitiesByVoucher(String voucherId) {
        return voucherItemRepository.getItemsQuantitiesByVoucher(voucherId);
    }
}
