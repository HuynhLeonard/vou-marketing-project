package com.voufinal.statistic_service.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateQuizWinnerReq {
    private Long idEvent;
    private Long idGame;
    private Long userId;
    private Integer rank;
}
