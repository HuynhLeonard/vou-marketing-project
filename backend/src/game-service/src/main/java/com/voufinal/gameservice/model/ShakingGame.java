package com.voufinal.gameservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ShakingGame {
    @Id
    private Long id_game;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_game")
    private Game game;

    public ShakingGame() {
    }
}
