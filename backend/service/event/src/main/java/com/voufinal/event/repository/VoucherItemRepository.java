package com.voufinal.event.repository;

import com.voufinal.event.entity.VoucherItem;
import com.voufinal.event.model.VoucherItemId;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherItemRepository extends JpaRepository<VoucherItem, VoucherItemId>, VoucherItemRepositoryCustom {
    VoucherItem findByVoucherAndItem(String voucherId, String itemId);
    Map<String, Integer> getItemsQuantitiesByVoucher(String voucherId);
    List<VoucherItem> findByVoucher(String voucherId);
}