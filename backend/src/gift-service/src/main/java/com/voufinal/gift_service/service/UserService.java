package com.voufinal.gift_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    private final String PLAYERS_SERVICE_URL = "http://localhost:8082/api/v1/players";

    public LinkedHashMap<String, Object> findPlayerByIdentifier(Object playerIdentifier, String type) throws Exception {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PLAYERS_SERVICE_URL);

            switch (type) {
                case "id_user":
                    builder.queryParam("id_user", playerIdentifier);
                    break;
                case "email":
                    builder.queryParam("email", playerIdentifier);
                    break;
                case "username":
                    builder.queryParam("username", playerIdentifier);
                    break;
            }

            String url = builder.toUriString();
            return (LinkedHashMap<String, Object>) restTemplate.getForEntity(url, Object.class).getBody();
        } catch (ResourceAccessException e) {
            throw new Exception("Unable to connect to the user management system");
        } catch (HttpClientErrorException.NotFound notFoundException) {
            throw new Exception("Could not find the recipient or sender with the provided id");
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new Exception("User management system error");
        }
    }
}
