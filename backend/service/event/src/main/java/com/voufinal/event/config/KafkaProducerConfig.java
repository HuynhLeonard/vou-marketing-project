package com.voufinal.event.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.voufinal.event.kafka.serializer.EventSessionInfoSerializer;
import com.voufinal.event.kafka.serializer.Notifcation_Event_Created_Data_Serializer;
import com.voufinal.event.model.EventSessionInfo;
import com.voufinal.event.model.Notifcation_Event_Created_Data;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, EventSessionInfo> eventSessionProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSessionInfoSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ProducerFactory<String, Notifcation_Event_Created_Data> notificationProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Notifcation_Event_Created_Data_Serializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, EventSessionInfo> kafkaTemplateEventSessionInfo() {
        return new KafkaTemplate<>(eventSessionProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, Notifcation_Event_Created_Data> kafkaTemplateNotificationInfo() {
        return new KafkaTemplate<>(notificationProducerFactory());
    }
}
