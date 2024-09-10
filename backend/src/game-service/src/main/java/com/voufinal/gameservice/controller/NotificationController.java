package com.voufinal.gameservice.controller;

import com.voufinal.gameservice.common.SuccessResponse;
import com.voufinal.gameservice.dto.EventDTO;
import com.voufinal.gameservice.dto.GameInfoDTO;
import com.voufinal.gameservice.dto.Notification;
import com.voufinal.gameservice.dto.QuizDTO;
import com.voufinal.gameservice.model.Game;
import com.voufinal.gameservice.model.Quiz;
import com.voufinal.gameservice.service.NotificationConsumerService;
import com.voufinal.gameservice.socket.SocketModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class NotificationController {
    @Autowired
    NotificationConsumerService notificationConsumerService;
    private final Logger logger = LoggerFactory.getLogger(NotificationConsumerService.class);
    @Autowired
    SocketModule socketModule;
    @GetMapping("/notification")
    public ResponseEntity<?> getNotificationByUserName(@RequestParam String username){
        List<Notification> event = notificationConsumerService.getEventNotification(username);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Đã gửi thông báo sự kiện sắp kết thúc!", HttpStatus.OK, event));
    }

    @PostMapping("/ask-for-turn")
    public ResponseEntity<?> askForTurn(@RequestParam String sender, @RequestParam String receiver){
        String message= sender +" muốn xin 1 lượt của bạn";
        Map<String, Object> response = new HashMap<>();
        response.put("sender", sender);
        response.put("receiver", receiver);
        response.put("message",message);
        socketModule.sendNotification(message, sender, receiver, "turn_notification");
        logger.info("sender"+ message+"cua"+ receiver);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Đã gửi thông báo xin lượt!", HttpStatus.OK, response));
    }
}

