package com.voufinal.statistic_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RatesQuestionCorrect {
    private Long questionId;
    private Double rates;
}
