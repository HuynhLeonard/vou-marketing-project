package com.voufinal.session_service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voufinal.session_service.dto.MessageDto;
import com.voufinal.session_service.engine.GameEngine;
import com.voufinal.session_service.engine.quizgame.QuizGameEngine;
import com.voufinal.session_service.engine.shakinggame.ShakingGameEngine;
import com.voufinal.session_service.service.ISessionsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class SessionsController {
    private static final Logger log = LoggerFactory.getLogger(SessionsController.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private SimpMessagingTemplate messagingTemplate;
    private ISessionsService sessionsService;
    private QuizGameEngine quizGameEngine;
    private ShakingGameEngine shakingGameEngine;

    @MessageMapping("/game")
    public void executeGame(MessageDto message, SimpMessageHeaderAccessor headerAccessor) {
        String playerId, sessionId, gameType;
        JsonNode reqNode;
        GameEngine gameEngine = quizGameEngine;

        try {
            log.info("payload: {}", message.getPayload());
            reqNode = objectMapper.readTree(message.getPayload());
            playerId = reqNode.get("playerId").asText();
            sessionId = reqNode.get("sessionId").asText();
            gameType = reqNode.get("gameType").asText();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot parse payload");
        }

        log.info("Message type {}", message.getType());
        log.info("Game type {}", gameType);

        if (gameType.equals("shaking")) {
            gameEngine = shakingGameEngine;
        }

        switch (message.getType()) {
            case CONNECTION:
                log.info("Connect game ...");
                headerAccessor.getSessionAttributes().put("sessionId", sessionId);
                headerAccessor.getSessionAttributes().put("playerId", playerId);
                headerAccessor.getSessionAttributes().put("gameType", gameType);
                messagingTemplate.convertAndSend("/topic/connection/" + sessionId, gameEngine.connect(sessionId));
                break;
            case DISCONNECT:
                log.info("Disconnect game ...");
                gameEngine.disconnect(sessionId, playerId);
                break;
            case START:
                log.info("Start game ...");
                log.info("Game started, playerId: {}", playerId);
                // Send quiz questions
                messagingTemplate.convertAndSend("/topic/start/" + sessionId + "/" + playerId, gameEngine.start(sessionId, playerId));
                break;
            case UPDATE:
                log.info("Update game ...");
                int score = reqNode.get("score").asInt();
                gameEngine.update(sessionId, playerId, score);
                break;
            case END:
                log.info("End game ...");
                messagingTemplate.convertAndSend("/topic/end/" + sessionId, gameEngine.end(sessionId));
                break;
            default:
                log.info("Invalid message type");
                break;
        }
    }

    @GetMapping("/api/sessions")
    public ResponseEntity<JsonNode> findActiveSession(@RequestParam("eventId") String eventId,
                                                      @RequestParam("gameId") String gameId,
                                                      @RequestParam("date") String date) {
        log.info("Find session by date {}, {}, {}", eventId, gameId, date);

        // Sử dụng ObjectMapper để chuyển đổi đối tượng sang JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String sessionId = sessionsService.findSessionByEventIdAndGameIdAndDate(eventId, gameId, LocalDate.parse(date)).getId().toHexString();

        try {
            JsonNode json = objectMapper.createObjectNode().put("sessionId", sessionId);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            log.error("Error creating JSON response", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
