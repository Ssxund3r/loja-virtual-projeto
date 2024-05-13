package com.projeto.ssxund3r.lojavirtual;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.projeto.ssxund3r.lojavirtual.controller.PessoaController;
import com.projeto.ssxund3r.lojavirtual.enums.TipoEndereco;
import com.projeto.ssxund3r.lojavirtual.model.Endereco;
import com.projeto.ssxund3r.lojavirtual.model.PessoaFisica;
import com.projeto.ssxund3r.lojavirtual.model.PessoaJuridica;
import com.projeto.ssxund3r.lojavirtual.repository.PessoaRepository;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualProjetoApplication.class)
class TestPessoaUsuario extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	
	@Autowired
	private PessoaRepository pesssoaRepository;
	
	@Test
	void testCadPessoaJuridica() throws ExceptionProjetoLojaVirtualJava {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoaJuridica.setNome("Cleber Alves Machado");
		pessoaJuridica.setEmail("projetolojavirtual98@gmail.com");
		pessoaJuridica.setTelefone("33206659000158");
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
	
	

	@Test
	public void testCadPessoaFisica() throws ExceptionProjetoLojaVirtualJava {

		PessoaJuridica pessoaJuridica = pesssoaRepository.existeCnpjCadastrado("1647987989047");
		PessoaFisica pessoaFisica = new PessoaFisica();
		
		pessoaFisica.setCpf("267.139.790-92");
		pessoaFisica.setNome("Alex fernando");
		pessoaFisica.setEmail("gabriel.fcosta@gmail.com");
		pessoaFisica.setTelefone("45999795800");
		pessoaFisica.setEmpresa(pessoaJuridica);

		Endereco endereco1 = new Endereco();
		endereco1.setBairro("Jd Dias");
		endereco1.setCep("556556565");
		endereco1.setComplemento("Casa cinza");
		endereco1.setNumero("389");
		endereco1.setPessoa(pessoaFisica);
		endereco1.setRuaLogra("Av. são joao sexto");
		endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
		endereco1.setUf("PR");
		endereco1.setCidade("Curitiba");
		endereco1.setEmpresa(pessoaJuridica);

		Endereco endereco2 = new Endereco();
		endereco2.setBairro("Jd Maracana");
		endereco2.setCep("7878778");
		endereco2.setComplemento("Andar 4");
		endereco2.setNumero("555");
		endereco2.setPessoa(pessoaFisica);
		endereco2.setRuaLogra("Av. maringá");
		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco2.setUf("PR");
		endereco2.setCidade("Curitiba");
		endereco2.setEmpresa(pessoaJuridica);

		pessoaFisica.getEnderecos().add(endereco2);
		pessoaFisica.getEnderecos().add(endereco1);

		pessoaFisica = pessoaController.salvarPf(pessoaFisica).getBody();

		assertEquals(true, pessoaFisica.getId() > 0);

		for (Endereco endereco : pessoaFisica.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}

		assertEquals(2, pessoaFisica.getEnderecos().size());

	}

}
