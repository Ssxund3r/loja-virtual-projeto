package com.projeto.ssxund3r.lojavirtual;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.projeto.ssxund3r.lojavirtual.model.PessoaJuridica;
import com.projeto.ssxund3r.lojavirtual.repository.PessoaRepository;
import com.projeto.ssxund3r.lojavirtual.service.PessoaUserService;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualProjetoApplication.class)
public class TestPessoaUsuario extends TestCase {
	
	@Autowired
	private PessoaUserService pessoaUserService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void testCadPessoaFisica() {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setCnpj("12345698710001");
		pessoaJuridica.setNome("Binho da Costa");
		pessoaJuridica.setEmail("binho.morro@gmail.com");
		pessoaJuridica.setTelefone("516924870010");
		pessoaJuridica.setInscEstadual("5246859712300015");
		pessoaJuridica.setInscMunicipal("25654821540050");
		pessoaJuridica.setNomeFantasia("BINHO MORRO DO COCO SOLUÇÕES GERAIS LTDA.");
		pessoaJuridica.setRazaoSocial("SENHOR DOS ANEIS S.A");
		pessoaJuridica.setEmpresa(pessoaJuridica);
		
		pessoaRepository.save(pessoaJuridica);
		
		/*
		 * PessoaFisica pessoaFisica = new PessoaFisica();
		 * 
		 * pessoaFisica.setCpf("12345698755");
		 * pessoaFisica.setNome("Gabriel Filipe da Costa");
		 * pessoaFisica.setEmail("gabriel.fcosta@hotmail.com");
		 * pessoaFisica.setTelefone("5198788354407");
		 * pessoaFisica.setEmpresa(pessoaFisica);
		 */
		 
	}
	
}
