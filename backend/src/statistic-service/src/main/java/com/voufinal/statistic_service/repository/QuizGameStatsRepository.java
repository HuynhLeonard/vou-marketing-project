package com.voufinal.statistic_service.repository;

import com.voufinal.statistic_service.model.statistic.QuizGameStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizGameStatsRepository extends JpaRepository<QuizGameStats, Long> {
    QuizGameStats findByIdEvent(Long idEvent);
}
