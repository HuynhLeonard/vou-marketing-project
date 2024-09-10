package com.voufinal.statistic_service.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerFinalRank {
    private Long idEvent;

    private String playerUsername;

    private Integer rank;
}
