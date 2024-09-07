package com.voufinal.turnservice.repository;

import com.voufinal.turnservice.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnRepository extends JpaRepository<Turn, Long> {
    Turn findByIdPlayerAndIdGame(Long idPlayer, Long idGame);
}
