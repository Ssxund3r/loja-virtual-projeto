package com.projeto.ssxund3r.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.ssxund3r.lojavirtual.model.Acesso;

@Repository
@Transactional
public interface AcessoRepository  extends JpaRepository<Acesso, Long>{
	
}
