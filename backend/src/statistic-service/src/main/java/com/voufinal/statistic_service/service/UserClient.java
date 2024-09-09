package com.voufinal.statistic_service.service;

import com.voufinal.statistic_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserClient {
    private final RestTemplate restTemplate;
    private final String EVENTS_URL = "http://user-service:8082/api/v1/users";

    @Autowired
    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<List<User>> getUsers(List<Long> userIds) {
        try {
            HttpEntity<List<Long>> requestEntity = new HttpEntity<>(userIds);
            ResponseEntity<List<User>> response = restTemplate.exchange(
                    EVENTS_URL + "/statistics/users",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<>() {}
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                return Optional.ofNullable(response.getBody());
            }
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error user client in statistics: " + e.getMessage());
            return Optional.empty();
        }
    }
}
