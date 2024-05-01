package com.projeto.ssxund3r.lojavirtual.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.projeto.ssxund3r.lojavirtual.model.Usuario;
import com.projeto.ssxund3r.lojavirtual.repository.UsuarioRepository;

@Service
@Component
public class TarefaAutomatizadaService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	@Scheduled(initialDelay = 2000, fixedDelay = 86400000) //Roda a cada 24horas
	//@Scheduled(cron = "0 0 11 * * *", zone = "America/Sao_Paulo") //Rodar todo dia as 11 horas da manhã horas SP
	public void notificaUserTrocaSenha() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		
		List<Usuario> usuarios = usuarioRepository.usuarioSenhaVencida();
		
		for (Usuario usuario : usuarios) {
			
			StringBuilder msg = new StringBuilder();
			msg.append("Olá, ").append(usuario.getPessoa().getNome()).append("<br/>");
			msg.append("Está na hora de trocar sua senha, já passou 90 dias de validade.").append("<br/>");
			msg.append("Troque sua senha a projeto loja virtual - Gabriel C.");
			
			serviceSendEmail.enviarEmailHtml("Troca de senha", msg.toString(), usuario.getLogin());
			
			Thread.sleep(3000);
			
		}
	}
}
