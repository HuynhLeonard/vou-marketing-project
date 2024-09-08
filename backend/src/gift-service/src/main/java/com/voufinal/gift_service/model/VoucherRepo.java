package com.voufinal.gift_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "voucherrepo")
@Entity
public class VoucherRepo {
    @Id
    @Column(name = "id_voucherrepo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idVoucherRepo;

    @Column(name = "id_player")
    Long idPlayer;

    @Column(name = "code_voucher")
    String codeVoucher;

    @Column(name = "amount")
    Long amount;
}
