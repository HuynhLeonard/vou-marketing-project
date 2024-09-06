package com.voufinal.session_service.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class RecordEntity {
    @Field(value = "user_id")
    protected String userId;

    @Field(value = "total_time")
    protected long totalTime;
}
