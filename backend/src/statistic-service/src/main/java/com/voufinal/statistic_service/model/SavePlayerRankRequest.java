package com.voufinal.statistic_service.model;

import com.voufinal.statistic_service.model.request.PlayerFinalRank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Data
public class SavePlayerRankRequest {
    private List<PlayerFinalRank> list;
}