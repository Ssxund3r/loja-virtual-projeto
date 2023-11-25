package com.projeto.ssxund3r.lojavirtual.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.ssxund3r.lojavirtual.model.PessoaJuridica;

@Repository
public interface PessoaRepository  extends CrudRepository<PessoaJuridica, Long> {
	
}
