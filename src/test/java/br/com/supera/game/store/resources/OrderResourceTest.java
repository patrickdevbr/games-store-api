package br.com.supera.game.store.resources;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.supera.game.store.resources.requests.OrderItemRequest;

@SpringBootTest
public class OrderResourceTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void orderInsertTest() {
		try {
			OrderItemRequest oir1 = new OrderItemRequest();
			oir1.setProductId(1L);
			oir1.setQuantity(1);
			oir1.setPrice(BigDecimal.valueOf(189.98));
			OrderItemRequest oir2 = new OrderItemRequest();
			oir2.setProductId(8L);
			oir2.setQuantity(2);
			oir2.setPrice(BigDecimal.valueOf(204.39));
			OrderItemRequest oir3 = new OrderItemRequest();
			oir3.setProductId(4L);
			oir3.setQuantity(1);
			List<OrderItemRequest> list = List.of(oir1, oir2, oir3);

			String requestJson = new ObjectMapper().writeValueAsString(list);
			// @formatter:off
			mvc.perform(post("/orders")
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
	void addItemTest() {
		try {
			OrderItemRequest oir = new OrderItemRequest();
			oir.setProductId(4L);
			oir.setQuantity(2);
			oir.setPrice(BigDecimal.valueOf(69.83));

			String requestJson = new ObjectMapper().writeValueAsString(oir);
			// @formatter:off
			mvc.perform(post("/orders/1/items")
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
	void updateQuantityExistingItemTest() {
		try {
			OrderItemRequest oir = new OrderItemRequest();
			oir.setProductId(4L);
			oir.setQuantity(2);
			oir.setPrice(BigDecimal.valueOf(69.83));

			String requestJson = new ObjectMapper().writeValueAsString(oir);
			// @formatter:off
			mvc.perform(post("/orders/1/items")
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
	void removeItemTest() {
		try {
			OrderItemRequest oir = new OrderItemRequest();
			oir.setProductId(4L);
			oir.setQuantity(4);
			oir.setPrice(BigDecimal.valueOf(69.83));

			String requestJson = new ObjectMapper().writeValueAsString(oir);
			// @formatter:off
			mvc.perform(delete("/orders/1/items")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
			// @formatter:on
		} catch (Exception e) {
			fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
		}
	}

}
