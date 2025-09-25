package com.movie.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TB_ID")
	private Long movieId;

	@Column(name = "TB_NAME")
	private String name;

	@OneToMany(mappedBy = "movie")

	private List<Session> sessions = new ArrayList<>();

	public Movie() {

	}

	public Movie(Long movieId, String name, List<Session> sessions) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.sessions = sessions;
	}

	public Movie(String name) {

		this.name = name;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	@Override
	public String toString() {
		return ", name=" + name;
	}

	// Adicionar na lista de sessions
	public void addSession(Session session) {
		this.sessions.add(session);
		session.setMovie(this);

	}

	// Pegar a quantidade de sessoes totais que existe para gente mostrar no for e
	// trazer
	// a informação na nossa classe de test
	public int getTotalSession() {
		return sessions.size();
	}

}
