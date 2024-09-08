package com.voufinal.mc_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PlaySession")
@Entity
public class PlaySession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_playsession")
    private Long idPlaySession;

    @Column(name = "id_player")
    private Long idPlayer;

    @Column(name = "id_game")
    private Long idGame;

    @Column(name = "score")
    private int score;

    @Column(name = "turns")
    private int turns;

    public PlaySession(Long idPlayer, Long idGame, int score, int turns) {
        this.idGame = idGame;
        this.idPlayer = idPlayer;
        this.score = score;
        this.turns = turns;
    }
}
