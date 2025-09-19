package com.movie.service;


import org.springframework.stereotype.Service;

import com.movie.model.Compra;
import com.movie.repository.CompraRepository;

@Service
public class CompraService {

	private final CompraRepository compraRepository;

	public CompraService(CompraRepository compraRepository) {

		this.compraRepository = compraRepository;

	}

	public void Comprar(Compra compra) {
		compraRepository.save(compra);
	}

}
