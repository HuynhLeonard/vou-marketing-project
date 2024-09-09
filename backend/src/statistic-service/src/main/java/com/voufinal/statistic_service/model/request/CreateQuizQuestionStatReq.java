package com.voufinal.statistic_service.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateQuizQuestionStatReq {
    Long idQuizQuestion;
    Long idGame;
    Long idEvent;
    Boolean result;
}
