package com.movie.model;

import java.util.ArrayList;
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
	private int qtd;
	private double valorFinal;

	@OneToMany(mappedBy = "compra")
	private List<Ticket> tickets = new ArrayList<>();

	public Compra() {

	}

	public Compra(Long id, int qtd, double valorFinal, List<Ticket> tickets) {

		this.id = id;
		this.qtd = qtd;
		this.valorFinal = valorFinal;
		this.tickets = tickets;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
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

	/*
	 * public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
	 */

	// Adicionar mais de um e nao apenas um
	public void addTickets(List<Ticket> ticket) {
		this.tickets.addAll(ticket);
	}

}
