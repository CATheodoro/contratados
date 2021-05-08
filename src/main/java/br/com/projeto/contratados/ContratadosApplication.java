package br.com.projeto.contratados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@SpringBootApplication
@EnableSpringDataWebSupport
public class ContratadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContratadosApplication.class, args);
	}

}
