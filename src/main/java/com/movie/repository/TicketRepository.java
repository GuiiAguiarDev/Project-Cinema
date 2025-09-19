package com.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	// Verifica no banco quantos tem, tudo isso pelo Id
	// Nesse caso, esse metodo, ele está verificando quantos tickets há na sessão
	// Estou na classe do ticketRepository passando session, porque session tem
	// Tickets
	// Fica a dica de como fazer, quando eu quiser saber quanto tem no banco,m
	// quando envolve lista tbm
	int countBySessionId(Long sessionId);

}
