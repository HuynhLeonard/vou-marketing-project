package com.voufinal.statistic_service.model.statistic;

import com.voufinal.statistic_service.model.RatesQuestionCorrect;
import com.voufinal.statistic_service.model.StatisticResponse;
import com.voufinal.statistic_service.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QuizStatistic extends StatisticResponse {
    private List<User> winners;
    private List<RatesQuestionCorrect> correctRates;

    public QuizStatistic(Long participants) {
        super(participants);
    }
}
