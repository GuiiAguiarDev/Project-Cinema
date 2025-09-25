package com.movie.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.dto.MovieDTO;
import com.movie.model.Movie;
import com.movie.model.Session;
import com.movie.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service
public class MovieService {

	private final MovieRepository movieRepository;
	private final ObjectMapper objectMapper;

	public MovieService(MovieRepository movieRepository, ObjectMapper objectMapper) {

		this.movieRepository = movieRepository;
		this.objectMapper = objectMapper;
	}

	/*
	 * Importando de um api externa, quiser usar, desmarcar e usar, essa abaixo puxa
	 * de uma api que está externa, já está tudo configurado certo só descomnetar, e
	 * comentar a outra
	 */

	/*
	 * 
	 * public void importMoviesFromJsonApi() { try { URI uri = URI.create(
	 * "https://raw.githubusercontent.com/erik-sytnyk/movies-list/master/db.json");
	 * URL url = uri.toURL();
	 * 
	 * InputStream input = url.openStream(); JsonNode root =
	 * objectMapper.readTree(input);
	 * 
	 * JsonNode moviesArray = root.get("movies"); if (moviesArray.isArray()) { for
	 * (JsonNode movidNode : moviesArray) { MovieDTO movieDTO =
	 * objectMapper.treeToValue(movidNode, MovieDTO.class); Movie movie = new
	 * Movie(movieDTO.getTitle()); movieRepository.save(movie);
	 * 
	 * } } System.out.println("Films imported successfully!"); } catch (Exception e)
	 * { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 */

	/*
	 * Importar arquivo json interno, está no proprio projeto, nos criamos ela, pois
	 * não existe um api para puxar dados de filmes em cartaz no cinema, então
	 * criamos eae puxamos como codigo abaixo. Quiser usar a outra, comentar a baixo
	 * e descomentar a outra
	 */

	public void importMoviesFromJsonApi() {
		try {
			InputStream input = getClass().getResourceAsStream("/movies.json");
			JsonNode root = objectMapper.readTree(input);

			JsonNode moviesArray = root.get("movies");
			if (moviesArray.isArray()) {
				for (JsonNode movieNode : moviesArray) {
					MovieDTO movieDto = objectMapper.treeToValue(movieNode, MovieDTO.class);
					Movie movie = new Movie(movieDto.getTitle());
					movieRepository.save(movie);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transactional
	public Movie addMovieToSession(Long movieId, Session session) {
		// Como cadastramos os dados no banco vindo de uma api externa e interna, no
		// nosso caso
		// interna, temos que fazer assim para puxar(buscar) os dados do banco para
		// passar para
		// algum
		// lugar que queremos, no caso quando eu for cadastrar a session, quero
		// cadastrar
		// o movie, associado, então pego o id do movie tipo o id 1L é batman
		// então ele vai passar o 1L, agr se eu quiser o filme de acordo com a sequencia
		// certa, ou seja oq eu passar no mais e ele buscar
		// eu passo o movieID em vez de 1L ou 2L ou 3L e etc, que ai vai ser de acordo
		// como está no banco a informação que eu quero pelo mains
		Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not Found!"));

		// Adicionando o filme X na sessão X, porque vamos supor eu tenho o filme batman
		// ele pode esta na sessão 1 e 2 por exemplo então por isso a lista, um filme
		// pode esta
		// em varias sessoes
		// Para ver todos os filmes que estão na sessoes basta eu fazer o for geral e
		// nao o que eu
		// passo o size que é o int i=0, i++ e etc

		movie.addSession(session);

		return movieRepository.save(movie);

	}

	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	// no Application/Main nunca jamais devemos colocar lá if, for e etc, é apenas
	// para inserir ifnormações e chamar metodos e etc
	@Transactional
	public void listAllSessions() {
		List<Movie> allMovies = movieRepository.findAll();
		for (Movie m : allMovies) {
			for (Session s : m.getSessions()) {
				System.out.println("Informações do filme");
				System.out.println("Número Sessão: " + s.getId());
				System.out.println("Filme: " + s.getMovie().getName());
				System.out.println("Horario do Filme: " + s.getDateTime());
				System.out.println("Ingressos disponiveis " + s.getCapacidade());
				System.out.println("-----------------------------------");
				System.out.println("-----------------------------------");
			}

		}
	}

}
