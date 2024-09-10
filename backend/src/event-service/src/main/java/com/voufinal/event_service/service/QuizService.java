package com.voufinal.event_service.service;

import com.voufinal.event_service.dto.GameInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class QuizService {
    @Autowired
    private RestTemplate restTemplate;

    private final String QUIZ_SERVICE_URL = "http://localhost:8086/api/v1/game/quiz/create";

    public void createQuiz(GameInfoDto gameInfoDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        // Wrap the List<QuizDTO> in an HttpEntity
        HttpEntity<GameInfoDto> request = new HttpEntity<>(gameInfoDTO, headers);

        // Make the POST request
        ResponseEntity<Void> response = restTemplate.exchange(
                QUIZ_SERVICE_URL,
                HttpMethod.POST,
                request,
                Void.class
        );

        // Optionally handle the response
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Quizzes created successfully.");
        } else {
            System.out.println("Failed to create quizzes: " + response.getStatusCode());
        }
    }
    public GameInfoDto getGameInfo(Long eventId) {
        String url = "http://localhost:8086/api/v1/game/game-info?eventId=" + eventId;

        // Make the GET request
        ResponseEntity<GameInfoDto> response = restTemplate.getForEntity(url, GameInfoDto.class);

        // Return the body of the response
        return response.getBody();
    }
    public GameInfoDto updateGameInfo(GameInfoDto gameInfoDTO) {
        String url = "http://localhost:8086/api/v1/game/game-info?eventId=" + gameInfoDTO.getEventId();

        // Make a PUT request to the updateGameInfo endpoint
        ResponseEntity<GameInfoDto> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(gameInfoDTO),
                GameInfoDto.class
        );

        return response.getBody(); // Return the updated GameInfoDTO
    }

    public Integer getTurns(Long idPlayer, Long idGame) {
        String url = "http://localhost:8086/api/v1/game/" + idGame + "/players/" + idPlayer + "/turns";

        ResponseEntity<Integer> response = restTemplate.getForEntity(url, Integer.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }
}
