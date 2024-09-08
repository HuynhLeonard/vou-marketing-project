package com.voufinal.gift_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Table(name = "voucher")
@Entity
public class Voucher {
    @Id
    @Column(name = "code")
    String code;

    @Column(name = "qr_code")
    String qrCode;

    @Column(name = "voucher_name")
    String voucherName;

    @Column(name= "voucher_price")
    Long voucherPrice;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "expiration_date")
    Timestamp expirationDate;

    @Column(name = "description")
    String description;

    @Column(name = "type")
    String type;

    @Column(name = "id_item1")
    Long idItem1;

    @Column(name = "id_item2")
    Long idItem2;

    @Column(name = "id_item3")
    Long idItem3;

    @Column(name = "id_item4")
    Long idItem4;

    @Column(name = "id_item5")
    Long idItem5;

    @Column(name = "aim_coin")
    Long aimCoin;

    @Column(name = "id_event")
    Long idEvent;

    @Column(name = "status")
    String status;
}
