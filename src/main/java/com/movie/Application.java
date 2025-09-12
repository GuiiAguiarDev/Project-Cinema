package com.movie;

import com.movie.service.SessionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private final SessionService sessionService;

	Application(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
