package com.voufinal.mc_service.service;

import com.voufinal.mc_service.dto.EventDto;
import com.voufinal.mc_service.dto.NotificationDto;
import com.voufinal.mc_service.socket.SocketModule;
import com.voufinal.mc_service.socket.SocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationConsumerService {
    private final Logger logger = LoggerFactory.getLogger(NotificationConsumerService.class);

    @Autowired
    SocketModule socketModule;
    @Autowired
    private RestTemplate restTemplate;

    @KafkaListener(topics = "events", groupId = "group_id")
    public void consume(String message) {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        // Process the message and send a notification
        String keyword = "--";
        int index = message.indexOf(keyword);
        String username = message.substring(0, index).trim();
        message = message.substring(index).trim().substring(2);
        logger.info(username, message);
        socketModule.sendNotification(message,null, username,"event_notification");
    }



    public List<NotificationDto> getEventNotification(String username) {
        String url = "http://event-service:8083/api/v1/favourite-events/notification-event?username=" + username;

        EventDto[] eventsArray = restTemplate.getForObject(url, EventDto[].class);


        return eventsArray != null ? List.of(eventsArray).stream()
                .map(this::convertToNotification)
                .collect(Collectors.toList()) : List.of();



    }
    private NotificationDto convertToNotification(EventDto eventDTO) {
        NotificationDto notification = new NotificationDto();
        notification.setIdEvent(eventDTO.getIdEvent());
        notification.setImageUrl(eventDTO.getImageUrl());
        notification.setMessage("The event " + eventDTO.getEventName() + " will end soon!");

        long days = (eventDTO.getStartDate().getTime() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24);
        notification.setNumDay((int) days);

        return notification;
    }
}