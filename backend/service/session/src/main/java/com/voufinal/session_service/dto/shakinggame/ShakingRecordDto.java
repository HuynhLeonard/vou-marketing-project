package com.voufinal.session_service.dto.shakinggame;

import com.voufinal.session_service.dto.RecordDto;
import lombok.Data;

@Data
public class ShakingRecordDto extends RecordDto {
    private long turns;
}
