package com.projeto.ssxund3r.lojavirtual.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.ssxund3r.lojavirtual.model.Usuario;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	//Configurando o gerenciamento de autenticacao
	public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		//Obriga a autenticar URL
		super(new AntPathRequestMatcher(url));
		
		//Gerenciador de autenticacao
		setAuthenticationManager(authenticationManager);
	}
	
	protected JWTLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}
	
	//Retorna o usuário ao processar a autenticação 
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		
		return getAuthenticationManager()
				.authenticate(
						new UsernamePasswordAuthenticationToken(
								usuario.getLogin(), usuario.getSenha()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		try {
			new JWTTokenAutenticacaoService()
				.addAuthentication(response, authResult.getName());
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
}
