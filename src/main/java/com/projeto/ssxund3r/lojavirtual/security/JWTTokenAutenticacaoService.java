package com.projeto.ssxund3r.lojavirtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.projeto.ssxund3r.lojavirtual.ApplicationContextLoad;
import com.projeto.ssxund3r.lojavirtual.model.Usuario;
import com.projeto.ssxund3r.lojavirtual.repository.UsuarioRepository;

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
		
		liberacaoCors(response);
		
		//Aplicado para realizar testes no POSTMAN
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}
	
	//Retorna o usuário validado com token ou caso nao válido, retorna null
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
			
			//Faz a validacao do token para o usuário na requisição e obtem o USER
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(tokenLimpo)
					.getBody()
					.getSubject(); //ADMIN ou Gabriel
			
			if (user != null) {
				
				Usuario usuario = ApplicationContextLoad
						.getApplicationContext()
						.getBean(UsuarioRepository.class)
						.findUserByLogin(user);
				
				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(), 
							usuario.getSenha(),
							usuario.getAuthorities()); 
				}
			}
		}
		
		liberacaoCors(response);
		return null;
	}
		
	//Realizando a liberação contra erros de CORS no navegador
	private void liberacaoCors(HttpServletResponse response) {
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
	
}