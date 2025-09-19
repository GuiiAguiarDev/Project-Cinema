package com.movie.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Compra {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	private double valorFinal;

	@OneToMany(mappedBy = "compra")
	private List<Ticket> tickets;

	public Compra() {

	}

	public Compra(Long id, double valorFinal, List<Ticket> tickets) {

		this.id = id;
		this.valorFinal = valorFinal;
		this.tickets = tickets;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	/*public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}*/
	
	
	public void addTickets(Ticket ticket) {
		this.tickets.add(ticket);
		ticket.setCompra(this);
	}

	

}
