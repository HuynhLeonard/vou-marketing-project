package com.voufinal.mc_service.controller;

import com.voufinal.mc_service.common.SuccessResponse;
import com.voufinal.mc_service.dto.NotificationDto;
import com.voufinal.mc_service.socket.SocketModule;
import com.voufinal.mc_service.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class NotificationController {
    @Autowired
    NotificationConsumerService notificationConsumerService;

    @Autowired
    SocketModule socketModule;
    @GetMapping("/notification")
    public ResponseEntity<?> getNotificationByUserName(@RequestParam String username){
        List<NotificationDto> event = notificationConsumerService.getEventNotification(username);
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
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Đã gửi thông báo xin lượt!", HttpStatus.OK, response));
    }
}
