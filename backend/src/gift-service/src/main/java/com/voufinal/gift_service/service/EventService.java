package com.voufinal.gift_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class EventService {
    @Autowired
    private RestTemplate restTemplate;

    private final String EVENTS_SERVICE_URL = "http://event-service:8083/api/v1/events";

    public HttpStatusCode decreaseRemainingVoucher(Long id_event) throws Exception {
        String url = EVENTS_SERVICE_URL + "/" + id_event + "/remaining-vouchers";

        try {
            ResponseEntity<String> responseUpdateEvent = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    null,
                    String.class
            );

            return responseUpdateEvent.getStatusCode();
        } catch (
                ResourceAccessException e) {
            throw new Exception("Unable to connect to the event management system");
        } catch (
                HttpClientErrorException.NotFound notFoundException) {
            throw new Exception("Event not found with the provided id");
        } catch (
                HttpServerErrorException.InternalServerError e) {
            throw new Exception("Event management system error");
        } catch (Exception e) {
            throw new Exception("Unexpected system error: " + e.getMessage());
        }
    }
}
