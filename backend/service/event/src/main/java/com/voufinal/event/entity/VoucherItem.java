package com.voufinal.event.entity;

import com.voufinal.event.common.EventIntermediateTableStatus;
import com.voufinal.event.model.VoucherItemId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "vouchers_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(VoucherItemId.class)
public class VoucherItem implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "number_of_item")
    private int numberOfItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_status")
    private EventIntermediateTableStatus activeStatus;
}