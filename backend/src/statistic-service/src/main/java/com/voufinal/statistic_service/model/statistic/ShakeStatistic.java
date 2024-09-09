package com.voufinal.statistic_service.model.statistic;

import com.voufinal.statistic_service.model.StatisticResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShakeStatistic extends StatisticResponse {
    private Long remainingVouchers;
    private Long givenVouchers;
    private Long shareCount;
    public ShakeStatistic(Long participants) {
        super(participants);
    }
}
