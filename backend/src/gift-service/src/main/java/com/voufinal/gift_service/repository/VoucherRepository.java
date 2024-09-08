package com.voufinal.gift_service.repository;

import com.voufinal.gift_service.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, String> {
    Voucher findByCode(String code);
    Voucher findByIdEvent(Long idEvent);
    List<Voucher> findVouchersByIdEvent(Long idEvent);
    Boolean existsByCode(String code);

    List<Voucher> findVouchersByTypeIs(String type);
}
