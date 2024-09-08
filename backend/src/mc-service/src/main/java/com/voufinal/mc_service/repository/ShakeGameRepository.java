package com.voufinal.mc_service.repository;

import com.voufinal.mc_service.model.ShakeGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShakeGameRepository extends JpaRepository<ShakeGame, Long> {

}