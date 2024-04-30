package com.projeto.ssxund3r.lojavirtual.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.ssxund3r.lojavirtual.model.PessoaJuridica;
import com.projeto.ssxund3r.lojavirtual.model.Usuario;
import com.projeto.ssxund3r.lojavirtual.repository.PessoaRepository;
import com.projeto.ssxund3r.lojavirtual.repository.UsuarioRepository;

@Service
public class PessoaUserService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;

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
			
			usuarioRepository.insereAcessoUserPj(usuarioPj.getId());
			
			StringBuilder mensagemHtml = new StringBuilder();
			
			mensagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b><br/>");
			mensagemHtml.append("<b>Login: </b>"+pessoaJuridica.getEmail()+"<br/>");
			mensagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			mensagemHtml.append("Obrigado!");
			
			try {
				serviceSendEmail.enviarEmailHtml("Acesso Gerado Loja Virtual", mensagemHtml.toString(),
						pessoaJuridica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return pessoaJuridica;
	
	}

}
