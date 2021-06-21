package br.com.supera.game.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GamesStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesStoreApplication.class, args);
	}

}
