package com.voufinal.notification_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voufinal.notification_service.model.NotificationInfo;
import org.apache.kafka.common.serialization.Deserializer;

public class NotificationInfoDeserializer implements Deserializer<NotificationInfo> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public NotificationInfo deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, NotificationInfo.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing NotificationInfo", e);
        }
    }
}
