package com.movie.runner;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.movie.model.Movie;
import com.movie.model.Session;
import com.movie.model.Ticket;
import com.movie.repository.MovieRepository;
import com.movie.repository.SessionRepository;
import com.movie.service.MovieService;
import com.movie.service.SessionService;
import com.movie.service.TicketService;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

	private final MovieService movieService;

	private final SessionRepository sessionRepository;

	private final SessionService sessionService;

	private final TicketService ticketService;

	private final MovieRepository movieRepository;

	public CommandLineRunner(TicketService ticketService, SessionService sessionService,
			MovieRepository movieRepository, SessionRepository sessionRepository, MovieService movieService) {

		this.ticketService = ticketService;

		this.sessionService = sessionService;

		this.movieRepository = movieRepository;

		this.sessionRepository = sessionRepository;

		this.movieService = movieService;
	}

	@Override
	public void run(String... args) throws Exception {

		/* INICIO */

		// Cadastrando o SESSION no Test
		Session session = new Session();

		LocalDate date = LocalDate.of(2025, 9, 12);
		LocalTime time = LocalTime.of(18, 00);
		LocalDateTime dateTime = LocalDateTime.of(date, time);

		session.setDateTime(dateTime);
		session.setCapacidade(100);
		// Inserindo o movie na session(secao), pois session tem movie no relacionamento
		// to passando o filme 1L que pode se o batman por exemplo. AI voce pode se
		// perguntar
		// Mas de onde vem o 1L, esse 1L ele vem á da classe SessionService, eu fiz um
		// metodo
		// onde faço um findbyid para pegar o filme pelo id, nome do metodo la é
		// addMovieTossession
		// exatamanente o nome que to chamando abaixo, caso queira ver mais especifico,
		// ir la
		// na classe verificar
		movieService.addMovieToSession(1L, session);
		sessionService.saveSession(session);
		System.out.println("Sessão salva");

		// FIM

		// Cadastrando o sessio no Test

		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------

		/* INICIO */

		// Cadastrando o TICKET no Test

		// Instanciando um ticket(ingresso) e passando seu valor
		Ticket ticket = new Ticket();
		ticket.setValor(20);
		// Ticket tem a sessao ou seja para eu cadastrar o ticket tenho que cadastrar um
		// sessao
		// relacionar ele com a sessao, pois um ticket está relacionado a uma sessao,
		// mas uma sessao
		// tem varios tickects
		// Ai voce se pergunta, mas pq no caso do session em relação ao filme eu tive
		// que criar um metodo na service para adicionar na lista por lá tbm e tudo
		// mais, e
		// aqui nao
		// precisa, sendo que é a mesma coisa?
		// Porque lá no caso eu precisei fazer uma busca, então sempre quando eu fizer
		// uma busca
		// e precisar adiciona a infoes na lista tambem porque é many e tem lista, do
		// mesmo jeito que está lá
		// eu tenho que criar o metodo na service igual eu fiz lá, aqui eu nao preciso
		// de nada
		// disso por isso simplesmente passar direito
		// lembrando que la como eu fiz a busca direto eu nao conseguiria pela a
		// instancia dela
		// inserir as informações na lista, no caso, como fiz isso abaixo lá
		// Movie movie = movieRepository.findById(1L).orElseThrow(() -> new
		// RuntimeException("Movie not Found!"));
		// eu não posso pegar esse movie e passar na litsa, então mais um motivo por ter
		// feito por la
		// pq se faço isso aqui da erro.
		ticket.setSession(session);

		// Mesma coisa doq expliquei acima
		Ticket ticket2 = new Ticket();
		ticket2.setValor(40);
		ticket2.setSession(session);
		// Mesma coisa doq expliquei acima
		Ticket ticket3 = new Ticket();
		ticket3.setValor(40);
		ticket3.setSession(session);
		// Mesma coisa doq expliquei acima
		Ticket ticket4 = new Ticket();
		ticket4.setValor(40);
		ticket4.setSession(session);

		/* Adicionando na minha lista de tickets a session relacionados */
		session.addTicket(ticket);
		session.addTicket(ticket2);
		session.addTicket(ticket3);
		session.addTicket(ticket4);

		// Salvando tickets no banco com tudo certo
		ticketService.saveTicket(ticket);
		ticketService.saveTicket(ticket2);
		ticketService.saveTicket(ticket3);
		ticketService.saveTicket(ticket4);
		System.out.println("Salvo");

		/*
		 * 
		 * /* Fim
		 */

		// Cadastrando o TICKET no Test

		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------

		/* Inicio */

		// Cadastrando o Movie no Test

		Movie movie = new Movie();

		// Salvando na lista de sesssoes o filme
		movie.addSession(session);

		System.out.println(movie.getTotalSession());

		/* Fim */

		// Cadastrando o Movie no Test

		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------

		System.out.println("INFORMAÇÔES DO SISTEMA");
		System.out.println("--------------------");
		for (Ticket t : session.getTicket()) {
			System.out.println("Informações do filme");
			System.out.println("--------------------");
			System.out.println("NÚMERO SESSÂO: " + t.getSession().getId());
			System.out.println("FILME: " + t.getSession().getMovie().getName());
			System.out.println("Total Vendidos:" + session.GetTicketsSold());
		}

		// OBSERVAÇÃOI PARA NAO ESQUECER, EU FALO ASSIM NA MINHA LISTA VOU TER
		// EXEMPLO ESSA DO FILME EU FALO NA MINHA LISTA DE MOVIE VOU TER VARAS
		// SESSESIONS
		// PORQUE VOU TER UMA LISTA DE FILMES EXEMPLO BATMAN, SUPERMAN E PARA CADA FILME
		// DESSE VOU TER SESSOES, FICA DICA

	}
}
