package com.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movie.model.Session;
import com.movie.model.Ticket;
import com.movie.repository.SessionRepository;
import com.movie.repository.TicketRepository;

@Service
public class TicketService {

	private final SessionRepository sessionRepository;

	private final TicketRepository ticketRepository;

	public TicketService(TicketRepository ticketRepository, SessionRepository sessionRepository) {

		this.ticketRepository = ticketRepository;

		this.sessionRepository = sessionRepository;

	}

	public void saveTicket(List<Ticket> tickets) {
		// verifica a que session pertece o ticket para contar certo
		Session session = tickets.get(0).getSession();

		// verifica se há tickets salvos no banco relacionado a essa session para pegar
		// tambem
		// caso já tem alguns cadastrados ele pega, nao podemos passar da capoacidade
		// permitida
		long ticketsInDb = ticketRepository.countBySessionId(session.getId());

		// Esse + é para somar os ja existintes no banco e os que foram inserindos agora
		// caso foram
		// tudo isso em cima da session relacionada ai ele soma e ve se é maior que a
		// capacidade
		// então se no banco já tenho 3 ese cadasytrei mais 3 e a capacidade é 5, não
		// vai salvar
		// pois estorou, passou da capacidade permitida.
		//lembrando que o size é para pegar o total
		if (ticketsInDb + tickets.size() > session.getCapacidade()) {
			System.out.println("Capacity exess");
		} else {
			ticketRepository.saveAll(tickets);
			System.out.println("Tickets Registered!");
		}


	}

}
