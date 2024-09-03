package com.voufinal.event.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.voufinal.event.dto.BrandDto;

@FeignClient(name = "users-service", url = "http://users:8081/users")
public interface UserServiceClient {

    @GetMapping("/brands/{brandId}")
    BrandDto getBrand(@PathVariable String brandId);

    @PostMapping("/brands/public/emails")
    List<BrandDto> getBrandsByEmails(@RequestBody List<String> emails);
}