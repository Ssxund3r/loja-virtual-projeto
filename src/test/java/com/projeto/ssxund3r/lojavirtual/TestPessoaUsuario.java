package com.projeto.ssxund3r.lojavirtual;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.projeto.ssxund3r.lojavirtual.controller.PessoaController;
import com.projeto.ssxund3r.lojavirtual.enums.TipoEndereco;
import com.projeto.ssxund3r.lojavirtual.model.Endereco;
import com.projeto.ssxund3r.lojavirtual.model.PessoaJuridica;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualProjetoApplication.class)
class TestPessoaUsuario extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	
	@Test
	void testCadPessoaJuridica() throws ExceptionProjetoLojaVirtualJava {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoaJuridica.setNome("Cleber Alves Machado");
		pessoaJuridica.setEmail("tata.raiz@gmail.com");
		pessoaJuridica.setTelefone("516924870010");
		pessoaJuridica.setInscEstadual("5246859712300015");
		pessoaJuridica.setInscMunicipal("25654821540050");
		pessoaJuridica.setNomeFantasia("SUNGA_PLANISMO LTDA.");
		pessoaJuridica.setRazaoSocial("ENTREGADORES DE CAMISETA S.A");
		pessoaJuridica.setEmpresa(pessoaJuridica);
		
		Endereco endereco1 = new Endereco();
		
		endereco1.setBairro("Entregador Coletes");
		endereco1.setCep("651235880");
		endereco1.setComplemento("Centro Treineiro");
		endereco1.setEmpresa(pessoaJuridica);
		endereco1.setNumero("2436");
		endereco1.setPessoa(pessoaJuridica);
		endereco1.setRuaLogra("Deus Sunga - Praia");
		endereco1.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco1.setCidade("Sungalândia");
		endereco1.setUf("RJ");
		
		Endereco endereco2 = new Endereco();
		
		endereco2.setBairro("Bairro teste");
		endereco2.setCep("94155030");
		endereco2.setComplemento("Centro Teste");
		endereco2.setEmpresa(pessoaJuridica);
		endereco2.setNumero("1278");
		endereco2.setPessoa(pessoaJuridica);
		endereco2.setRuaLogra("Saint Vicent");
		endereco2.setTipoEndereco(TipoEndereco.COBRANCA);
		endereco2.setCidade("Gravataí");
		endereco2.setUf("RS");
		
		pessoaJuridica.getEnderecos().add(endereco1);
		pessoaJuridica.getEnderecos().add(endereco2);
				
		pessoaJuridica  = pessoaController.salvarPj(pessoaJuridica).getBody();
		
		assertEquals(true, pessoaJuridica.getId() > 0);
		
		for (Endereco endereco : pessoaJuridica.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}
		
		assertEquals(2, pessoaJuridica.getEnderecos().size());
		assertTrue(pessoaJuridica.getEnderecos().contains(endereco1));
        assertTrue(pessoaJuridica.getEnderecos().contains(endereco2));
        assertTrue("Os objetos endereco1 e endereco2 devem ser diferentes! ", endereco1 != endereco2);
        assertNotNull(pessoaJuridica);
        assertNotNull(pessoaJuridica.getId());
        	
	}
	
}
