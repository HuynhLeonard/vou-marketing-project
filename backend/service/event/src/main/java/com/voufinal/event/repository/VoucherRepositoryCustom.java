package com.voufinal.event.repository;

import java.util.List;

import com.voufinal.event.entity.Voucher;

public interface VoucherRepositoryCustom {
    List<Voucher> findByBrand(String brandId);
    List<Voucher> findByBrands(List<String> brandIds);
    List<Voucher> findByIds(List<String> ids);
}
