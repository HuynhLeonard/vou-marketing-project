package com.voufinal.statistic_service.repository;

import com.voufinal.statistic_service.model.statistic.QuizQuestionStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionStatsRepository extends JpaRepository<QuizQuestionStats, Long> {

}
