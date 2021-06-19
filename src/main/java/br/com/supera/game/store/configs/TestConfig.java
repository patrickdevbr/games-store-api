package br.com.supera.game.store.configs;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.supera.game.store.entities.Product;
import br.com.supera.game.store.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		Product p1 = new Product(null, "Super Mario Odyssey", BigDecimal.valueOf(197.88), (short) 100,
				"super-mario-odyssey.png");
		Product p2 = new Product(null, "Call Of Duty Infinite Warfare", BigDecimal.valueOf(49.99), (short) 80,
				"call-of-duty-infinite-warfare.png");
		Product p3 = new Product(null, "The Witcher III Wild Hunt", BigDecimal.valueOf(119.5), (short) 250,
				"the-witcher-iii-wild-hunt.png");
		Product p4 = new Product(null, "Call Of Duty WWII", BigDecimal.valueOf(249.99), (short) 205,
				"call-of-duty-wwii.png");
		Product p5 = new Product(null, "Mortal Kombat XL", BigDecimal.valueOf(69.99), (short) 150,
				"mortal-kombat-xl.png");
		Product p6 = new Product(null, "Shards of Darkness", BigDecimal.valueOf(71.94), (short) 400,
				"shards-of-darkness.png");
		Product p7 = new Product(null, "Terra Média: Sombras de Mordor", BigDecimal.valueOf(79.99), (short) 50,
				"terra-media-sombras-de-mordor.png");
		Product p8 = new Product(null, "FIFA 18", BigDecimal.valueOf(195.39), (short) 325, "fifa-18.png");
		Product p9 = new Product(null, "Horizon Zero Dawn", BigDecimal.valueOf(115.8), (short) 290,
				"horizon-zero-dawn.png");

		productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9));
	}

}
