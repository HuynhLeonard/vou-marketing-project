package com.voufinal.statistic_service.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateQuizGameStatReq {
    private Long idEvent;

    private Long idGame;
}
