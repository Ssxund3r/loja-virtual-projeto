package com.projeto.ssxund3r.lojavirtual.service;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.ssxund3r.lojavirtual.model.PessoaFisica;
import com.projeto.ssxund3r.lojavirtual.model.PessoaJuridica;
import com.projeto.ssxund3r.lojavirtual.model.Usuario;
import com.projeto.ssxund3r.lojavirtual.repository.PessoaFisicaRepository;
import com.projeto.ssxund3r.lojavirtual.repository.PessoaRepository;
import com.projeto.ssxund3r.lojavirtual.repository.UsuarioRepository;

@Service
public class PessoaUserService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	@PersistenceContext
    private EntityManager entityManager;  
	
	public Boolean possuiAcesso(String username, String acessos) {

		String sql = " select count(1) > 0 from usuarios_acesso as ua "
				+ " inner join usuario as u on u.id = ua.usuario_id "
				+ " inner join acesso as a on a.id = ua.acesso_id " + " where u.login = '" + username + "' "
				+ " and a.descricao in (" + acessos + ")";

		Query query = entityManager.createNativeQuery(sql);

		return Boolean.valueOf(query.getSingleResult().toString());

	}
	
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) {
		
		for(int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
			pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
			pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
		}
		
		pessoaJuridica = pessoaRepository.save(pessoaJuridica);

		Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaJuridica.getId(), pessoaJuridica.getEmail());

		if (usuarioPj == null) {

			String constraint = usuarioRepository.consultaConstraintAcesso();

			if (constraint != null) {
				
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint + "; commit;");
		
			}
			
			usuarioPj = new Usuario();
			usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPj.setEmpresa(pessoaJuridica);
			usuarioPj.setPessoa(pessoaJuridica);
			usuarioPj.setLogin(pessoaJuridica.getEmail());

			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = new BCryptPasswordEncoder().encode(senha);

			usuarioPj.setSenha(senhaCript);

			usuarioPj = usuarioRepository.save(usuarioPj);
			
			usuarioRepository.insereAcessoUser(usuarioPj.getId());
			//usuarioRepository.insereAcessoUserPj(usuarioPj.getId(), "ROLE_ADMIN");
			
			
			  StringBuilder mensagemHtml = new StringBuilder();
			  
			  mensagemHtml.
			  append("<b>Segue abaixo seus dados de acesso para a loja virtual</b><br/>");
			  mensagemHtml.append("<b>Login: </b>"+pessoaJuridica.getEmail()+"<br/>");
			  mensagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			  mensagemHtml.append("Obrigado!");
			  
			  try { serviceSendEmail.enviarEmailHtml("Acesso Gerado Loja Virtual",
			  mensagemHtml.toString(), pessoaJuridica.getEmail()); } catch (Exception e) {
			  e.printStackTrace(); }
			 
		}
		
		return pessoaJuridica;
	
	}

	public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {
	
		for (int i = 0; i< pessoaFisica.getEnderecos().size(); i++) {
			pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
			//pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaFisica);
		}
		
		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica); 
		
		Usuario usuarioPf = usuarioRepository.findUserByPessoa(pessoaFisica.getId(), pessoaFisica.getEmail());
		
		if (usuarioPf == null) {
			
			String constraint = usuarioRepository.consultaConstraintAcesso();
			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
			}
			
			usuarioPf = new Usuario();
			usuarioPf.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPf.setEmpresa(pessoaFisica.getEmpresa());
			usuarioPf.setPessoa(pessoaFisica);
			usuarioPf.setLogin(pessoaFisica.getEmail());
			
			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = new BCryptPasswordEncoder().encode(senha);
			
			usuarioPf.setSenha(senhaCript);
			
			usuarioPf = usuarioRepository.save(usuarioPf);
			
			usuarioRepository.insereAcessoUser(usuarioPf.getId());
			
			StringBuilder menssagemHtml = new StringBuilder();
			
			menssagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b><br/>");
			menssagemHtml.append("<b>Login: </b>"+pessoaFisica.getEmail()+"<br/>");
			menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			menssagemHtml.append("Obrigado!");
			
			pessoaFisica.setSenhaTemp(senha);
			
			try {
			  serviceSendEmail.enviarEmailHtml("Acesso Gerado para Loja Virtual", menssagemHtml.toString() , pessoaFisica.getEmail());
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return pessoaFisica;
	}

}
