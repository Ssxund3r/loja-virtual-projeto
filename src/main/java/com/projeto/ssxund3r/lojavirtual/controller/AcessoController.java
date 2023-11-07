package com.projeto.ssxund3r.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.ssxund3r.lojavirtual.model.Acesso;
import com.projeto.ssxund3r.lojavirtual.repository.AcessoRepository;
import com.projeto.ssxund3r.lojavirtual.service.AcessoService;

@Controller
@RestController
public class AcessoController {

	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acessoRepository;

	@PostMapping("**/salvarAcesso") // Mapeando a URL para receber um JSON
	@ResponseBody // Poder dar um retorno da API
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { // Recebe o JSON e converte para objeto
		
		Acesso acessoSalvo = acessoService.save(acesso);

		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}
	
	@PostMapping("**/deleteAcesso") 
	@ResponseBody 
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {
		
		acessoRepository.deleteById(acesso.getId());

		return new ResponseEntity("Acesso Removido! ",HttpStatus.OK);
	}
}