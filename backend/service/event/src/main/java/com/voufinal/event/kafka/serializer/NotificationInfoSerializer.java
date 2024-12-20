package com.voufinal.event.kafka.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voufinal.event.model.NotificationInfo;
import org.apache.kafka.common.serialization.Serializer;

public class NotificationInfoSerializer implements Serializer<NotificationInfo> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, NotificationInfo data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing NotificationInfo", e);
        }
    }
}
