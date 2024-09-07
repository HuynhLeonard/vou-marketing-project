package com.voufinal.gameservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class QuizGame {
    @Id
    private Long id_game;

    private int aim_score;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_game")
    private Game game;

    public QuizGame(int aim_score) {
        this.aim_score = aim_score;
    }
}

