package com.projeto.ssxund3r.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.ssxund3r.lojavirtual.ExceptionProjetoLojaVirtualJava;
import com.projeto.ssxund3r.lojavirtual.model.PessoaFisica;
import com.projeto.ssxund3r.lojavirtual.model.PessoaJuridica;
import com.projeto.ssxund3r.lojavirtual.repository.PessoaRepository;
import com.projeto.ssxund3r.lojavirtual.service.PessoaUserService;
import com.projeto.ssxund3r.lojavirtual.util.ValidaCNPJ;
import com.projeto.ssxund3r.lojavirtual.util.ValidaCPF;

@RestController
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaUserService pessoaUserService;

	@ResponseBody
	@PostMapping(value = "**/salvarPj")
	public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica)
			throws ExceptionProjetoLojaVirtualJava {
		try {
			if (pessoaJuridica == null) {
				throw new ExceptionProjetoLojaVirtualJava("Pessoa Juridica Não Pode Ser NULL! ");
			}

			if (pessoaJuridica.getId() == null
					&& pessoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
				throw new ExceptionProjetoLojaVirtualJava(
						"Já existe CNPJ cadastrado com o número: " + pessoaJuridica.getCnpj());
			}

			if (!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
				throw new ExceptionProjetoLojaVirtualJava("CNPJ: " + pessoaJuridica.getCnpj() + " está inválido.");
			}

			pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);

			return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
		} catch (ExceptionProjetoLojaVirtualJava ex) {
			   throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
			    
		}
	}
	
	@ResponseBody
	@PostMapping(value = "**/salvarPf")
	public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica)
			throws ExceptionProjetoLojaVirtualJava {
		try {
			if (pessoaFisica == null) {
				throw new ExceptionProjetoLojaVirtualJava("Pessoa Física Não Pode Ser NULL! ");
			}

			if (pessoaFisica.getId() == null
					&& pessoaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
				throw new ExceptionProjetoLojaVirtualJava(
						"Já existe CPF cadastrado com o número: " + pessoaFisica.getCpf());
			}

			if (!ValidaCPF.isCPF(pessoaFisica.getCpf())) {
				throw new ExceptionProjetoLojaVirtualJava("CPF: " + pessoaFisica.getCpf() + " está inválido.");
			}

			//pessoaFisica = pessoaUserService.salvarPessoaFisica(pessoaFisica);

			return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
		} catch (ExceptionProjetoLojaVirtualJava ex) {
			   throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
			    
		}
	}
}
