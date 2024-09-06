package com.voufinal.session_service.engine.quizgame;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuizResponse implements Serializable {
    private int responseCode;
    private List<QuizQuestion> results;

}
