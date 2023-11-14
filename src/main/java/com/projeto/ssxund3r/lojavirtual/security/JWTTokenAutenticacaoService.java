package com.projeto.ssxund3r.lojavirtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenAutenticacaoService {
	
	//Token de validade de 11 dias 
	private static final long EXPIRATION_TIME = 959990000;
	
	//Chave de senha para juntar com JWT 
	private static final String SECRET = "s/-*-*sds565dszase-*-dsaxv";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	//Gera o token retorna a resposta para o cliente com JWT
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		//Montagem do Token 
		String JWT = Jwts.builder() //Chama o gerador de token
				.setSubject(username) //Adiciona o user
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); // Tempo de expiração
		
		//EXE: Bearer *-8+**8-8*8*-8*88*+8*-8-6564979464949ASDQ5WD164da51q564
		String token = TOKEN_PREFIX + " " + JWT;
		
		//Dá resposta para tela e para o cliente, outra API, navegador, aplicativo, JS, outra chamada...
		response.addHeader(HEADER_STRING, token);
		
		//Aplicado para realizar testes no POSTMAN
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
	
}
