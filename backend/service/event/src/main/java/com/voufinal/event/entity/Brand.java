// Temporary file to store the Brand entity
package com.voufinal.event.entity;

import com.voufinal.event.common.UserRole;
import com.voufinal.event.entity.BaseEntity;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "accountId")
    private String accountId;
    
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", insertable = false, updatable = false)
    private UserRole role;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "field")
    private String field;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "status")
    private boolean status;
}