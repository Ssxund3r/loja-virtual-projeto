package com.projeto.ssxund3r.lojavirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.projeto.ssxund3r.lojavirtual.model") 
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.projeto.ssxund3r.lojavirtual.repository"})
@EnableTransactionManagement 
public class LojaVirtualProjetoApplication {

	public static void main(String[] args) {
		
		//System.out.println(new BCryptPasswordEncoder().encode("123"));
		
		SpringApplication.run(LojaVirtualProjetoApplication.class, args);
	}

}
