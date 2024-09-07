package com.voufinal.event_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long quizId;
    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private int correctAnswerIndex;
}
