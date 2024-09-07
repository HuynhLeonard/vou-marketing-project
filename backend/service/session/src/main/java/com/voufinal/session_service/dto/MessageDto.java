package com.voufinal.session_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    private MessageType type;
    private String payload;
}