package com.voufinal.gameservice.repository;

import com.voufinal.gameservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByIdEvent(Long idEvent);
    Game findByIdGame(Long idGame);
}

