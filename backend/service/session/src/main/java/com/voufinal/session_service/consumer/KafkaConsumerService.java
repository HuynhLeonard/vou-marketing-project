package com.voufinal.session_service.consumer;

import com.voufinal.session_service.dto.SessionDto;
import com.voufinal.session_service.engine.GameEngine;
import com.voufinal.session_service.engine.shakinggame.ShakingGameEngine;
import com.voufinal.session_service.engine.quizgame.QuizGameEngine;
import com.voufinal.session_service.model.EventSessionInfo;
import com.voufinal.session_service.service.ISessionsService;
import com.voufinal.session_service.utils.Utils;
import com.voufinal.session_service.schedule.SchedulerService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final List<EventSessionInfo> messages = new ArrayList<>();
    private ISessionsService sessionsService;
    private SchedulerService schedulerService;
    private SimpMessagingTemplate messagingTemplate;

    private QuizGameEngine quizGameEngine;

    private ShakingGameEngine shakingGameEngine;

    @KafkaListener(topics = "event-session", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory"
            , concurrency = "1")
    public void listen(ConsumerRecord<String, EventSessionInfo> record, Acknowledgment acknowledgment) {
        EventSessionInfo eventSessionInfo = record.value();
        messages.add(eventSessionInfo);

        String gameId = eventSessionInfo.getGameId();
        String eventId = eventSessionInfo.getEventId();
        String startDate = eventSessionInfo.getStartDate();
        String endDate = eventSessionInfo.getEndDate();
        String startTime = eventSessionInfo.getStartTime() + ":00";
        String endTime = eventSessionInfo.getEndTime() + ":00";
        String gameType = eventSessionInfo.getGameType();

        log.info("Start date : {}", startDate);
        log.info("End date : {}", endDate);
        log.info("Start time : {}", startTime);
        log.info("End time : {}", endTime);
        log.info("Game type : {}", gameType);

        Runnable setUpGame = () -> {
            log.info("SET UP GAME");
            // Save data to MongoDB and get sessionId
            // Assume this game is quiz game
            SessionDto sessionDto = new SessionDto();
            sessionDto.setEventId(eventId);
            sessionDto.setGameId(gameId);
            sessionDto.setUsers(new ArrayList<>());
            sessionDto.setDate(ZonedDateTime.now().toLocalDate());
            SessionDto createdSessionDto = sessionsService.createSession(sessionDto);

            log.info("New session entity: {}", createdSessionDto);
            String sessionId;
            GameEngine gameEngine;
            switch (gameType) {
                case "quiz":
                    gameEngine = quizGameEngine;
                    sessionId = "66d5611b50080825d7cda679";
                    break;
                case "shaking":
                    gameEngine = shakingGameEngine;
                    sessionId = createdSessionDto.getId().toHexString();
                    break;
                default:
                    throw new RuntimeException("Failed to set up game session");
            }

            gameEngine.setUp(sessionId);
//            Runnable endGame = () -> {
//                log.info("END GAME, SAVE TO MONGODB AND DELETE REDIS");
//                List<QuizRecordDto> leaderboard = gameEngine.end(sessionId);
//                log.info("Leaderboard: {}", leaderboard);
//                messagingTemplate.convertAndSend("/topic/end/" + sessionId, leaderboard);
//            };

            Runnable updateTime = () -> {
                updateTimeAndLeaderboard(sessionId);
            };
//            schedulerService.createCronJobs(endGame, startDate, endDate, endTime, endTime, false);
            schedulerService.createCronJobs(updateTime, startDate, endDate, startTime, endTime, true);
        };

        schedulerService.createCronJobs(setUpGame, startDate, endDate, startTime, endTime, false);

        acknowledgment.acknowledge();
    }

    private void updateTimeAndLeaderboard(String sessionId) {
        long now = Utils.now();
        log.info("Time: {}", now);
        messagingTemplate.convertAndSend("/topic/time/" + sessionId, now);
    }

    public List<EventSessionInfo> getMessages() {
        return new ArrayList<>(messages);
    }

    public EventSessionInfo getNewestMessage() {
        if (messages.isEmpty()) {
            return null; // Return null if there are no messages
        }
        return messages.get(messages.size() - 1);
    }

    public void clearMessages() {
        messages.clear();
    }
}
