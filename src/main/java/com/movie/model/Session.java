package com.movie.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Session {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private LocalDateTime dateTime;
	private int capacidade;

	@ManyToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

	@OneToMany(mappedBy = "session")
	private List<Ticket> ticket = new ArrayList<>();

	public Session() {

	}

	public Session(Long id, LocalDateTime dateTime, int capacidade, Movie movie, List<Ticket> ticket) {

		this.id = id;
		this.dateTime = dateTime;
		this.capacidade = capacidade;
		this.movie = movie;
		this.ticket = ticket;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Ticket> getTicket() {
		return ticket;
	}

	// List nao tem set então vamos comentar abaixo e fazer um add substituindo, no
	// caso um metodo
	/*
	 * public void setTicket(List<Ticket> ticket) { this.ticket = ticket; }
	 * 
	 */

	public void addTicket(Ticket ticket) {
		this.ticket.add(ticket);
		ticket.setSession(this);
	}

	public int GetTicketsSold() {
		// Pegando a quantidade de ingressos vendidos, porque é ingresso por sessão
		// ou sej auma sessao pode ter varios ingressos ai pegamos aqui cada vez que ele
		// adiciona um ingresso na lista, ou pegamos o total que foi adicionado mesmo.
		return ticket.size();
	}


}
