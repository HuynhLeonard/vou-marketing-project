package com.voufinal.gift_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "itemrepo")
@Entity
public class ItemRepo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_itemrepo")
    Long idItemRepo;

    @Column(name = "id_player")
    Long idPlayer;

    @Column(name = "id_item")
    Long idItem;

    @Column(name = "amount")
    Long amount;
}