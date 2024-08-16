package com.voufinal.brand_service.repository;

import com.voufinal.brand_service.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, String> {
    Optional<Voucher> findById(String id);
}
