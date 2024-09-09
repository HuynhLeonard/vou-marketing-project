package com.voufinal.statistic_service.model.statistic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quiz_question_stats")
public class QuizQuestionStatistic {
    @Id
    @Column(name = "id_quiz_question")
    private Long idQuizQuestion;

    @Column(name = "id_game")
    private Long idGame;

    @Column(name = "id_event")
    private Long idEvent;

    @Column(name = "correct_count")
    private Long correctCount;

    @Column(name = "incorrect_count")
    private Long incorrectCount;
}
