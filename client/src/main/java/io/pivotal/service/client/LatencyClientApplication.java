package io.pivotal.service.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LatencyClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LatencyClientApplication.class, args);
	}
}