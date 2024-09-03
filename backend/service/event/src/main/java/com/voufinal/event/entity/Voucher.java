package com.voufinal.event.entity;

import com.voufinal.event.common.VoucherStatus;
import com.voufinal.event.common.VoucherUnitValue;
import com.voufinal.event.entity.BaseEntity;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vouchers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Voucher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // @ManyToOne
    // @JoinColumn(name = "brand_id", nullable = false)
    // private Brand brand;

    @Column(name = "brand_id")
    private String brand_id;

    @Column(name = "voucher_code")
    private String voucherCode;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "image")
    private String image;

    @Column(name = "value")
    private double value;

    @Column(name = "description")
    private String description;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    @Column(name = "status")
    private VoucherStatus status;

    @Column(name = "unit_value")
    private VoucherUnitValue unitValue;
}