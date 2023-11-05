package com.projeto.ssxund3r.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projeto.ssxund3r.lojavirtual.controller.AcessoController;
import com.projeto.ssxund3r.lojavirtual.model.Acesso;

@SpringBootTest(classes = LojaVirtualProjetoApplication.class)
class LojaVirtualProjetoApplicationTests {

	@Autowired
	private AcessoController acessoController;
	
	@Test
	void testCadastraAcesso() {
		Acesso acesso = new Acesso();

		acesso.setDescricao("ROLE_ADMIN");
		
		acessoController.salvarAcesso(acesso);
	}

}
