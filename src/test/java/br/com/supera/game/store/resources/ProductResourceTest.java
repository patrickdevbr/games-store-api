package br.com.supera.game.store.resources;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.supera.game.store.entities.Product;

@SpringBootTest
public class ProductResourceTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void productInsertTest() {
		try {
			Product p = new Product(null, "Crash Titans", BigDecimal.valueOf(39.39), (short) 320, "crash-titans.png");

			String requestJson = new ObjectMapper().writeValueAsString(p);
			// @formatter:off
			mvc.perform(post("/products")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
			// @formatter:on	
		} catch (Exception e) {
			fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
		}
	}

	@Test
	void productUpdateTest() {
		try {
			Product p = new Product(null, "Crash of The Titans", BigDecimal.valueOf(28.47), (short) 320,
					"crash-of-the-titans.png");

			String requestJson = new ObjectMapper().writeValueAsString(p);
			// @formatter:off
			mvc.perform(put("/products/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
			// @formatter:on
		} catch (Exception e) {
			fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
		}
	}

	@Test
	void productDeleteTest() {
		try {
			// @formatter:off
			mvc.perform(delete("/products/10")
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
			// @formatter:on
		} catch (Exception e) {
			fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
		}
	}

}
