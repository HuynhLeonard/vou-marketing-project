package com.voufinal.statistic_service.model.statistic;

import com.voufinal.statistic_service.model.StatisticsResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShakeStatistics extends StatisticsResponse {
    private Long remainingVouchers;
    private Long givenVouchers;
    private Long shareCount;
    public ShakeStatistics(Long participants) {
        super(participants);
    }
}
