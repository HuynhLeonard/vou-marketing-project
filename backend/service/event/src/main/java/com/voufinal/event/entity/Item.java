package com.voufinal.event.entity;

import com.voufinal.event.entity.BaseEntity;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // @ManyToOne
    // @JoinColumn(name = "brand_id", nullable = false)
    // private Brand brand;

    @Column(name = "brand_id")
    private String brand_id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "description")
    private String description;
}