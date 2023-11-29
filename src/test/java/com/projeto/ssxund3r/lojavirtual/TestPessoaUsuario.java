package com.projeto.ssxund3r.lojavirtual;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.projeto.ssxund3r.lojavirtual.controller.PessoaController;
import com.projeto.ssxund3r.lojavirtual.model.PessoaJuridica;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualProjetoApplication.class)
class TestPessoaUsuario extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	
	@Test
	void testCadPessoaFisica() throws ExceptionProjetoLojaVirtualJava {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoaJuridica.setNome("Binho da Costa");
		pessoaJuridica.setEmail("palmeiras.naoTemMundial@gmail.com");
		pessoaJuridica.setTelefone("516924870010");
		pessoaJuridica.setInscEstadual("5246859712300015");
		pessoaJuridica.setInscMunicipal("25654821540050");
		pessoaJuridica.setNomeFantasia("BINHO MORRO DO COCO SOLUÇÕES GERAIS LTDA.");
		pessoaJuridica.setRazaoSocial("SENHOR DOS ANEIS S.A");
		pessoaJuridica.setEmpresa(pessoaJuridica);
		
		pessoaController.salvarPj(pessoaJuridica);
		
	
		 
	}
	
}
