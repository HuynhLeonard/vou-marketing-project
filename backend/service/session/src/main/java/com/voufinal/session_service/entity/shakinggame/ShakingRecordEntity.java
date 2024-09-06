package com.voufinal.session_service.entity.shakinggame;

import com.voufinal.session_service.entity.RecordEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class ShakingRecordEntity extends RecordEntity {
    @Field(value = "turns")
    private long turns;
}
