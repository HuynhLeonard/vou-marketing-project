package com.voufinal.gameservice.controller;

import com.voufinal.gameservice.dto.EventDTO;
import com.voufinal.gameservice.dto.GameInfoDTO;
import com.voufinal.gameservice.dto.Notification;
import com.voufinal.gameservice.dto.QuizDTO;
import com.voufinal.gameservice.model.Game;
import com.voufinal.gameservice.model.Quiz;
import com.voufinal.gameservice.service.NotificationConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class NotificationController {
    @Autowired
    NotificationConsumerService notificationConsumerService;

    @GetMapping("/notification")
    public ResponseEntity<?> Notification(@RequestParam String username){
        List<Notification> event = notificationConsumerService.getEventNotification(username);
        return ResponseEntity.ok(event);
    }
}