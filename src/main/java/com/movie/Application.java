package com.movie;

import com.movie.model.Compra;
import com.movie.model.Movie;
import com.movie.model.Session;
import com.movie.model.Ticket;
import com.movie.repository.MovieRepository;
import com.movie.service.CompraService;
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
		var compraService = context.getBean(CompraService.class);

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
		session.setCapacidade(2);
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

		// ------------------------------------------------------------------------------------------

		// Rodando sistema Methods

		// Vamos lá para eu entender
		// O que eu queria fazer e aprender
		// Comprar um filme

		// Com o metodo FindAll eu vou buscar os filmes que cadastramos no sistema já
		// Então ele vai pegar os filmes cadastrados e passar para a lista.
		// Sempre quando a gente tiver algo cadastrado e quiser pegar todos esses dados
		// e jogar
		// para uma lista para depois acessar essas informações individualmente ou em um
		// for vendo todos
		// Fazer assim.
		// Lembrando que esse metodo esta na minha service, só verificar lá a
		// implementação dela
		// é bem simples
		List<Movie> filmesDisponiveis = movieService.findAll();

		System.out.println("Qual filme gostaria de assistir ?");
		// Nos buscamos os filmes, como foi dito acima, passando eles para dentro da
		// lista
		// filmesDisponiveis, eles estão todos aqui, como quero mostrar eles todos de
		// uma vez
		// eu passo ele abaixo e coloco um size, que tem a função de pegar a quantidade
		// total de filmes
		// exemplo se tem rocky 1, rocky 2 e rocky 3 quer dizer que são um total de 3
		// filmes
		// ele pega o total em numero, então o codigo abaixo fala, enquanto o i for
		// menor que a quantidade
		// total de filmes, ele vai mostrar os dados, com isso, conseguimos mostrar
		// todos os filmes
		// que estão cadastrados
		for (int i = 0; i < filmesDisponiveis.size(); i++) {
			System.out
					.println(filmesDisponiveis.get(i).getMovieId() + " Filme - " + filmesDisponiveis.get(i).getName());
		}
		// Aqui eu escolho o filme que quero ja que perguntei com systemout logo acima e
		// depois mostrei as
		// opcoes com for, colocamos o - 1 pq inicia no 0, mas mostramos apartir do 1,
		// então arrumamos isso
		int selectMovieIndex = sc.nextInt() - 1;

		// Aqui a gente cria uma variavel que vai receber o filme que vamos
		// escolher/escolhemos
		// Eu pego a lista que tem todos os filmes que puxamos pelo metodo que é o
		// filmesDisponiveis, e passo a resposta da pergunta qual filme gostaria de
		// assistir que é o
		// selectMpvieIndex
		// do filme que escolhemos, com isso vamos ter exatamente o filme certo dentro
		// do filmeEscolhido
		// Se foi rocky 1, rocky 2 ou rocky 3
		Movie filmeEscolhido = filmesDisponiveis.get(selectMovieIndex);

		// Sessao
		// Aqui criamos uma lista para receber as sessoes ja cadastradas em nosso
		// sistema relacionada a um filme que ja esta cadastrado tbm
		// vamos buscar essas sessoes tambem pelo metodo com nome de findByIdSessions,
		// que criamos
		// na nossa classe com nome de SessionService, ir lá para ler e entender oq o
		// metodo faz.
		// Passamos o metodo aqui, e como vimos no SessionService no metodo
		// findByIdSessions
		// ele precisa de um parametro, que é o id do filme, então vamos passar aqui,
		// a variavel filmeEscolhido que codamos acima, que ele tinha a função de pegar
		// o
		// filme que escolhemos, passando ele n o parametro, e a gente vai ter as
		// sessoes
		// disponiveis para esse filme, ou seja, se lá em cima, eu cadastrei a sessão 1
		// e
		// sessão 2
		// para o filme do Rocky 2, vai me mostrar todas essas sessoes disponiveis po
		// exemplo para o filme rocky 2 se eu selecionar ele fazendo ficar o numero do
		// id dele na variavel filme escolhido
		List<Session> sessions = sessionService.findByIdSessions(filmeEscolhido.getMovieId());

		// Aqui mostramos o resultado de todas as sessoes disponiveis para o filme que
		// selecionamos e solicitamos que o cliente escolha o sessão que ele quer
		System.out.println("Escolha sessao");
		for (Session s : sessions) {
			System.out.println(s.getId() + " " + s.getDateTime());
		}
		// Quando o cliente selecionar a sessão que ele quer, vai ir para dentro da
		// variavel com nome de sessionID
		Long sessionId = sc.nextLong();

		// Esse metodo é para gente passar para a variavel a sessao que escolhemos, que
		// vem do atributo acima com nome de sessionId
		// da resposta, então eu vou ver lá as seguintes infortmações, sessão 1 rocky 2
		// Sessão 2 rocky 2
		// eu escolho qual eu quero, se escolho a 2, por exemplo, ela(numero 2) vai ir
		// para a
		// variavel com nome de sessaoEscolhida, tenho que passar no metodo com
		// nome de
		// findSessionById, ou seja lá nele, eu coloco a sessionId no parametro, nao
		// posso passar sessionId
		// direto, sem o metodo, porque a resposta que o
		// sessionId recebe
		// vem de uma lista, ent fica a dica tbm, sempre quando for assim temos que
		// passar fazendo um metodo
		// Esse metodo, está na classe SessionService tbm, para ver ele basta ir na
		// classe SessionService
		Session sessaoEscholhida = sessionService.findSessionById(sessionId);

		System.out.println("Quantos ingressos deseja comprar");
		int qtdTickets = sc.nextInt();

		Compra comp1 = new Compra();
		compraService.comprar(comp1, sessaoEscholhida, qtdTickets);

	}

}