package com.movie;

import com.movie.model.Movie;
import com.movie.model.Session;
import com.movie.model.Ticket;
import com.movie.service.SessionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationWithAnnotation {

	SessionService sessionService;

	ApplicationWithAnnotation(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public static void main(String[] args) {
		var context = SpringApplication.run(ApplicationWithAnnotation.class, args);

		var movieService = context.getBean(com.movie.service.MovieService.class);
		var sessionService = context.getBean(SessionService.class);
		var ticketService = context.getBean(com.movie.service.TicketService.class);

		movieService.importMoviesFromJsonApi();

		// Iniciando tudo - Instancias objetos sempre primeiro e depois colocar tudo na
		// ordem
		// certa
		Movie movie = new Movie();
		Session session = new Session();

		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------

		/* Inicio */

		// Cadastrando o Movie no Test

		// Salvando na lista de sesssoes o filme
		movie.addSession(session);
		session.setMovie(movie);
		System.out.println(movie.getTotalSession());

		/* Fim */

		// Cadastrando o Movie no Test

		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------

		/* INICIO */

		// Cadastrando o SESSION no Test

		LocalDate date = LocalDate.of(2025, 9, 12);
		LocalTime time = LocalTime.of(18, 00);
		LocalDateTime dateTime = LocalDateTime.of(date, time);

		session.setDateTime(dateTime);
		session.setCapacidade(6);
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
		// eu não posso pegar esse movie e passar na lista, então mais um motivo por ter
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
		// Mesma coisa doq expliquei acima
		Ticket ticket5 = new Ticket();
		ticket5.setValor(40);
		ticket5.setSession(session);

		/* Adicionando na minha lista de tickets a session relacionados */
		session.addTicket(ticket);
		session.addTicket(ticket2);
		session.addTicket(ticket3);
		session.addTicket(ticket4);
		session.addTicket(ticket5);

		// Salvando tickets no banco com tudo certo, tudo de uma vez sem precisar
		// salvando um por
		// um
		ticketService.saveTicket(Arrays.asList(ticket, ticket2, ticket3, ticket4, ticket5));

		System.out.println("Salvo");

		/*
		 * 
		 * /* Fim
		 */

		// Cadastrando o TICKET no Test

		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------

		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");
		// Esse Exemplo abaixo fiz sem o for o outro mais abaixo fiz com o for
		// deixei esses dois exemplos para mostrar que posso fazer das duas maneiras
		// a diferença é que com o for eu posso mostrar mais de um item, exemplo tenho 5
		// tickects
		// cadastrados para essa sessção e quero mostrar cada uma ai uso o for, por
		// exemeplo
		// pois é para percorrer
		System.out.println("Informações do filme");
		System.out.println("Número Sessão: " + session.getId());
		System.out.println("Filme: " + session.getMovie().getName());
		System.out.println("Horario do Filme: " + session.getDateTime());
		System.out.println("Ingressos disponiveis " + session.getCapacidade());

		// OUTRA SESSÂO
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");

		Movie movie1 = new Movie();
		Session session1 = new Session();

		movie1.addSession(session1);
		session1.setMovie(movie1);
		System.out.println(movie1.getTotalSession());

		LocalDate date1 = LocalDate.of(2025, 9, 16);
		LocalTime time1 = LocalTime.of(22, 00);
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

		// OBSERVAÇÃOI PARA NAO ESQUECER, EU FALO ASSIM NA MINHA LISTA VOU TER
		// EXEMPLO ESSA DO FILME EU FALO NA MINHA LISTA DE MOVIE VOU TER VARAS
		// SESSESIONS
		// PORQUE VOU TER UMA LISTA DE FILMES EXEMPLO BATMAN, SUPERMAN E PARA CADA FILME
		// DESSE VOU TER SESSOES, FICA DICA

		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");

		// Methods Action

		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------
		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");

		// Um exemplo de quando usar o for para percorrer uma lista de dados, no caso eu
		// criei uma uma lista aqui tbm para jogar os dados das outras lista para
		// a gente percorerr todos os dados de uma vez, fica dica.
		// percorro as duas listas trazendo o horarios de todas as sessoes cadastradas.

		ArrayList<Session> sessionTotal = new ArrayList<>();

		sessionTotal.add(session);
		sessionTotal.add(session1);

		for (Session s : sessionTotal) {
			System.out.println("Teste" + s.getDateTime());
		}

		System.out.println("oi");

	}

}
