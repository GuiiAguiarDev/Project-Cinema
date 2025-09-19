package com.movie;

import com.movie.model.Movie;
import com.movie.model.Session;
import com.movie.model.Ticket;
import com.movie.service.MovieService;
import com.movie.service.SessionService;
import com.movie.service.TicketService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	SessionService sessionService;

	Application(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		var context = SpringApplication.run(Application.class, args);

		var movieService = context.getBean(MovieService.class);
		var sessionService = context.getBean(SessionService.class);
		var ticketService = context.getBean(TicketService.class);

		movieService.importMoviesFromJsonApi();

		Movie movie = new Movie();
		Session session = new Session();

		movie.addSession(session);
		session.setMovie(movie);
		System.out.println(movie.getTotalSession());

		LocalDate date = LocalDate.of(2025, 9, 12);
		LocalTime time = LocalTime.of(18, 0);
		LocalDateTime dateTime = LocalDateTime.of(date, time);

		session.setDateTime(dateTime);
		session.setCapacidade(6);
		movieService.addMovieToSession(1L, session);
		sessionService.saveSession(session);
		System.out.println("Sessão salva");

		Ticket ticket = new Ticket();
		ticket.setValor(20);
		ticket.setSession(session);

		Ticket ticket2 = new Ticket();
		ticket2.setValor(40);
		ticket2.setSession(session);

		Ticket ticket3 = new Ticket();
		ticket3.setValor(40);
		ticket3.setSession(session);

		Ticket ticket4 = new Ticket();
		ticket4.setValor(40);
		ticket4.setSession(session);

		Ticket ticket5 = new Ticket();
		ticket5.setValor(40);
		ticket5.setSession(session);

		session.addTicket(ticket);
		session.addTicket(ticket2);
		session.addTicket(ticket3);
		session.addTicket(ticket4);
		session.addTicket(ticket5);

		ticketService.saveTicket(Arrays.asList(ticket, ticket2, ticket3, ticket4, ticket5));
		System.out.println("Salvo");

		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");
		System.out.println("Informações do filme");
		System.out.println("Número Sessão: " + session.getId());
		System.out.println("Filme: " + session.getMovie().getName());
		System.out.println("Horario do Filme: " + session.getDateTime());
		System.out.println("Ingressos disponiveis " + session.getCapacidade());

		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");

		Movie movie1 = new Movie();
		Session session1 = new Session();

		movie1.addSession(session1);
		session1.setMovie(movie1);
		System.out.println(movie1.getTotalSession());

		LocalDate date1 = LocalDate.of(2025, 9, 16);
		LocalTime time1 = LocalTime.of(22, 0);
		LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);

		session1.setDateTime(dateTime1);
		session1.setCapacidade(1);
		movieService.addMovieToSession(3L, session1);
		sessionService.saveSession(session1);
		System.out.println("Sessão salva");

		Ticket ticket1 = new Ticket();
		ticket1.setValor(20);
		ticket1.setSession(session1);

		session1.addTicket(ticket1);

		ticketService.saveTicket(Arrays.asList(ticket1));
		System.out.println("Salvo");

		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");

		for (Session s : movie1.getSessions()) {
			System.out.println("Informações do filme");
			System.out.println("Número Sessão: " + s.getId());
			System.out.println("Filme: " + s.getMovie().getName());
			System.out.println("Horario do Filme: " + s.getDateTime());
			System.out.println("Ingressos disponiveis " + s.getCapacidade());
		}

		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");

		// Rodando sistema Methods

		int resp = 0;
		//Lista dos filmes para teste
		List<Movie> listaMovies = new ArrayList<>();
		while (resp != 2) {
			System.out.println("Qual filme gostaria de assistir ?");
			System.out.println(movie.getMovieId());

			int filmeEscolhido = sc.nextInt();

			listaMovies.add(listaMovies.get(filmeEscolhido));
			System.out.println("Deseja comprar mais um filme - 1-sim / 2-não");
			resp = sc.nextInt();
		}

		// Pegando o total de filmes que foram vendido, invocação do mal e superman
		// invocação é codigo 1 e superman é 2
		// toda vez que um filme é comprado ele adiciona o numero na lista
		// eae minha lista fica assim 1, 2 , 1, 1 e etc ai quero pegar o numero dentro
		// Esse metodo me mostra quantas vezes tal numero apareceu na lista, porem com o
		// filter, ele pega exatamente oq queremos, nos passando, um por uma
		// agr se quero de forma automatica, exemplo, compraram 10 filmes e ele ja vai
		// percorrer
		// esses 10 sem precisar eu ficar vendo um por um e sim ele fazer tudo pra mim
		// só utilizar o collectors.groupingBy, então vou comentar o filter e deixar o
		// groupingBy

		Map<Object, Long> count = listaMovies.stream()
				.collect(Collectors.groupingBy(f -> f, Collectors.counting()));
		count.forEach((filmeId, qtd) -> System.out
				.println("Filme do id " + filmeId + "foi comprado " + qtd));

		// long qtdCompraFilmes = IdDosFilmesComprados.stream().filter(n -> n ==
		// 2).count();
		//System.out.println("Quantidade de ingressos vendidos do filme " + qtdCompraFilmes);
	}

}