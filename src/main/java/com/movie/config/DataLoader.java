package com.movie.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.movie.service.MovieService;

@Component
public class DataLoader implements CommandLineRunner {

	// Essa classe serve para sempre quando rodamos nosso sistema ele fazer tudo de
	// novo
	// como estou usando h2 toda vez que roda ele reinicia então precisamos dessa
	// classe por isso
	// agora tivesse usando um banco real sem ser de teste nao precisaria

	private final MovieService movieService;

	public DataLoader(MovieService movieService) {

		this.movieService = movieService;
	}

	@Override
	public void run(String... args) {
		movieService.importMoviesFromJsonApi();
	}

}
