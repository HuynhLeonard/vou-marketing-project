package com.voufinal.session_service.dto.quizgame;

import com.voufinal.session_service.dto.RecordDto;
import lombok.Data;

@Data
public class QuizRecordDto extends RecordDto {
    private long totalScore;
}
