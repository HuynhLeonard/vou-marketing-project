package com.voufinal.session_service.entity.quizgame;

import com.voufinal.session_service.entity.RecordEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class QuizRecordEntity extends RecordEntity {
    @Field(value = "total_score")
    private long totalScore;
}
