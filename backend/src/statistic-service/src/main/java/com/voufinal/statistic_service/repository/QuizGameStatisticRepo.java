package com.voufinal.statistic_service.repository;

import com.voufinal.statistic_service.model.statistic.QuizGameStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizGameStatisticRepo extends JpaRepository<QuizGameStatistic, Long> {
    QuizGameStatistic findByIdEventAndIdGame(Long idEvent, Long idGame);
}
