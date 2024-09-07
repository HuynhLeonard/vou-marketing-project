package com.voufinal.gameservice.repository;

import com.voufinal.gameservice.model.QuizGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizGameRepository extends JpaRepository<QuizGame, Long> {
}
