package com.voufinal.notification_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voufinal.notification_service.model.NotificationEventCreatedData;
import org.apache.kafka.common.serialization.Deserializer;

public class NotificationEventCreatedDataDeserializer implements Deserializer<NotificationEventCreatedData> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public NotificationEventCreatedData deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, NotificationEventCreatedData.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing Notifcation_Event_Created_Data", e);
        }
    }
}
