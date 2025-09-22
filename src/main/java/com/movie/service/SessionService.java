package com.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.exception.ResourceNotFoundException;
import com.movie.model.Session;
import com.movie.repository.SessionRepository;

import jakarta.transaction.Transactional;

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

	// Esse metodo é para eu buscar o filme que está relacionado com a sessão
	// então por exemplo, acima ( lá na classe do application no main) quando
	// inserir as informações do movie e session
	// eu relacionei eles, exemplo, na sessão 1 vai esta o filme do rocky 2 exemplo
	// eae com esse metodo eu busco o movie que vai está relacionado a sessão
	// eae ja fica a dica para fazer outros exemplos em outros projetos
	// caso eu queira saber oq está relacionado com que e trazer os dados,
	// tipo, relacionei marca com carro eae quero trazer os carros que estão
	// vinculados
	// com a marca
	// Para entender o restante doq esse metodo esta fazendo, ir na classe
	// Application e procurar ele lá
	@Transactional
	public List<Session> findByIdSessions(Long movieId) {
		return sessionRepository.findByMovieMovieId(movieId);
	}

	// Serve apenas para gente passar a sessão corretamente, que escolhemos pq nao
	// podemos passar
	// direto
	// se nao da erro pois vem de uma lista, codamos esse metodo la no main tbm para
	// ver so ir la
	// procurar pelo nome dele, passo porque nao podemos passar direto eae ele serve
	// pra isso
	@Transactional
	public Session findSessionById(Long sessionId) {

		Session s = sessionRepository.findById(sessionId).orElseThrow(() -> new ResourceNotFoundException("Erro"));
		s.getTicket().size();
		return s;
	}
}
