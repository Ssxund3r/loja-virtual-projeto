package com.projeto.ssxund3r.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.ssxund3r.lojavirtual.model.Acesso;
import com.projeto.ssxund3r.lojavirtual.repository.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;

	public Acesso save(Acesso acesso) {
		/*
		 * Podemos realizar qualquer tipo de operação antes de salvar como regras de
		 * negócio etc...
		 */
		return acessoRepository.save(acesso);
	}
}