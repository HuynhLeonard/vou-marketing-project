package com.voufinal.event.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.voufinal.event.dto.AddUserRequestDto;

@FeignClient(name = "notifications-service", url = "http://notifications:8086/notifications/api/notifications")
public interface NotificationServiceClient {

    @PostMapping("/users")
    String addUsersToNotification(@RequestBody AddUserRequestDto addUsersRequestDto);
}