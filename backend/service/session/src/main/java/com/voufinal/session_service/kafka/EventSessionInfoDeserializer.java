package com.voufinal.session_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voufinal.session_service.model.EventSessionInfo;
import org.apache.kafka.common.serialization.Deserializer;

public class EventSessionInfoDeserializer implements Deserializer<EventSessionInfo> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public EventSessionInfo deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, EventSessionInfo.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing EventSessionInfo", e);
        }
    }
}
