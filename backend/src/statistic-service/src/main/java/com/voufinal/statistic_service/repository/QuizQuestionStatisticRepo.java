package com.voufinal.statistic_service.repository;

import com.voufinal.statistic_service.model.statistic.QuizQuestionStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionStatisticRepo extends JpaRepository<QuizQuestionStatistic, Long> {
    QuizQuestionStatistic findByIdQuizQuestion(Long quizQuestionId);

    List<QuizQuestionStatistic> findQuizQuestionStatsByIdEventAndIdGame(Long idEvent, Long idGame);
}
