package com.voufinal.brand_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "brand")
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String brandname;

    @Column(name = "password")
    private String password;

    @Column(name = "category")
    private String category;

    @Column(name = "status")
    private boolean status = false;  // allow by admin
}
