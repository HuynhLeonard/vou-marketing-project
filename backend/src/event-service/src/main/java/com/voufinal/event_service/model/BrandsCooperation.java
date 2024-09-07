package com.voufinal.event_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "brandscooperation")
public class BrandsCooperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event_id_brand")
    private Long idBrandCooperation;

    @Column(name = "id_event")
    private Long idEvent;

    @Column(name = "id_brand")
    private Long idBrand;

    @Column(name= "name_brand")
    private String nameBrand;
}
