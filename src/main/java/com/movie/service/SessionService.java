package com.movie.service;

import org.springframework.stereotype.Service;

import com.br.exception.ResourceNotFoundException;
import com.movie.model.Session;
import com.movie.repository.SessionRepository;


@Service
public class SessionService {

	private final SessionRepository sessionRepository;

	public SessionService(SessionRepository sessionRepository) {

		this.sessionRepository = sessionRepository;
	}

	public void saveSession(Session session) {
		sessionRepository.save(session);
	}


	public int totalCapacidade(int capacidade) {
		return capacidade;
	}

}
