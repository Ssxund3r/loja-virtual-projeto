package com.projeto.ssxund3r.lojavirtual.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.ssxund3r.lojavirtual.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository  extends CrudRepository<Usuario, Long>{
	
	@Query(value = "select u from Usuario u where u.login = ?1")
	Usuario findUserByLogin(String login);
}
