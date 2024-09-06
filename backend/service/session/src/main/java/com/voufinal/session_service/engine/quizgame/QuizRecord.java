package com.voufinal.session_service.engine.quizgame;

import com.voufinal.session_service.engine.Record;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class QuizRecord extends Record implements Serializable {
    private long totalScore;
}
