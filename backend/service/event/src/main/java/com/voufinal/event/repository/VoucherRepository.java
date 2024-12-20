package com.voufinal.event.repository;

import com.voufinal.event.entity.Voucher;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String>, VoucherRepositoryCustom {
    List<Voucher> findByBrand(String brandId);
    List<Voucher> findByBrands(List<String> brandIds);
    List<Voucher> findByIds(List<String> ids);
}