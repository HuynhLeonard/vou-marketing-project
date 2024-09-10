package com.voufinal.statistic_service.model.statistic;

import com.voufinal.statistic_service.model.QuestionCorrectRates;
import com.voufinal.statistic_service.model.QuizWinnerMetadata;
import com.voufinal.statistic_service.model.StatisticsResponse;
import com.voufinal.statistic_service.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class QuizStatistics extends StatisticsResponse {
    private List<QuizWinnerMetadata> winners;
    private List<QuestionCorrectRates> correctRates;

    public QuizStatistics(Long participants) {
        super(participants);
    }

    public QuizStatistics() {
        super(0L);
        winners = new ArrayList<>();
        correctRates = new ArrayList<>();
    }
}
