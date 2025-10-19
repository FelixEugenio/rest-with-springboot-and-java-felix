package pt.com.felix; // Define pacote da classe

import org.springframework.boot.SpringApplication; // Classe que inicia a aplicação
import org.springframework.boot.autoconfigure.SpringBootApplication; // Ativa auto-configuração do Spring
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication // Marca esta classe como a principal da aplicação Spring Boot
@EnableCaching
public class Startup {

	public static void main(String[] args) { // Ponto de entrada do programa
		SpringApplication.run(Startup.class, args);  // Inicia a aplicação Spring Boot

	}

}
