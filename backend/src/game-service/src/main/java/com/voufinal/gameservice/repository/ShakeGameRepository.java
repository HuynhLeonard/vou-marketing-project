package com.voufinal.gameservice.repository;

import com.voufinal.gameservice.model.ShakeGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShakeGameRepository extends JpaRepository<ShakeGame, Long> {
}
