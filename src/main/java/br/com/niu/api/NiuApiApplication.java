package br.com.niu.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan({"br.com.niu.api.model"}) /*varre os objetos modelos do pacote da aplicacao*/
@ComponentScan({"br.*"})/*varre todos os arquivos do projeto*/
@EnableJpaRepositories({"br.com.niu.api.repository"})/*mapeia as interfaces que comunica com a base*/
@EnableTransactionManagement /*controle de transação*/
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class NiuApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiuApiApplication.class, args);
	}

}
