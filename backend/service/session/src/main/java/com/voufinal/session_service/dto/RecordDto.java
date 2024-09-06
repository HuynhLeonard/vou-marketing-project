package com.voufinal.session_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordDto {
    protected String userId;
    protected long totalTime;
}
