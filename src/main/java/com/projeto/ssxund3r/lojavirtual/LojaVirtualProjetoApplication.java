package com.projeto.ssxund3r.lojavirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.projeto.ssxund3r.lojavirtual.model") 
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.projeto.ssxund3r.lojavirtual.repository"})
@EnableTransactionManagement 
public class LojaVirtualProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualProjetoApplication.class, args);
	}

}
