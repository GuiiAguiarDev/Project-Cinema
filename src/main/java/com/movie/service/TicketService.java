package com.movie.service;

import org.springframework.stereotype.Service;


import com.movie.model.Ticket;
import com.movie.repository.TicketRepository;

@Service
public class TicketService {

	private final TicketRepository ticketRepository;

	public TicketService(TicketRepository ticketRepository) {

		this.ticketRepository = ticketRepository;
	}

	public void saveTicket(Ticket ticket) {

	

		ticketRepository.save(ticket);
	}

}
