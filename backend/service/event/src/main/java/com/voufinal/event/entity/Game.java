package com.voufinal.event.entity;

import com.voufinal.event.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// @Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game extends BaseEntity {
    
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "type")
    private String type;

    @Column(name = "item_swappable")
    private boolean itemSwappable;

    @Column(name = "instruction")
    private String instruction;
}
