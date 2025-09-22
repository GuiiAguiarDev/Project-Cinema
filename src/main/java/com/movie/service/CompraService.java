package com.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.model.Compra;
import com.movie.model.Session;
import com.movie.model.Ticket;
import com.movie.repository.CompraRepository;
import com.movie.repository.SessionRepository;
import com.movie.repository.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Transactional
	public void comprar(Compra comp, Session session, int qtdTickts) {

		List<Ticket> ticketsDisponiveis = new ArrayList<>(session.getTicket());
		List<Ticket> ticketsComprados = new ArrayList<>();

		for (int i = 0; i < qtdTickts; i++) {
			if (!ticketsDisponiveis.isEmpty()) {
				Ticket t = ticketsDisponiveis.remove(0);
				t.setCompra(comp);
				ticketsComprados.add(t);
			} else {
				System.out.println("Não há disponiveis");
			}
		}
		comp.setQtd(ticketsComprados.size());
		comp.setValorFinal(ticketsComprados.stream().mapToDouble(Ticket::getValor).sum());
		comp.addTickets(ticketsComprados);

		compraRepository.save(comp);
		ticketRepository.saveAll(ticketsComprados);
		sessionRepository.save(session);
	}

}
