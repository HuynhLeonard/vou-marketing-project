package com.voufinal.statistic_service.repository;

import com.voufinal.statistic_service.model.QuizWinner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizWinnerRepo extends CrudRepository<QuizWinner, Long> {
    QuizWinner findByIdEventAndIdGame(Long idEvent, Long idGame);
    List<QuizWinner> findQuizWinnersByIdEventAndIdGame(Long idEvent, Long idGame);
}
