package com.voufinal.session_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
@EnableScheduling
public class SessionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionServiceApplication.class, args);
	}

}
