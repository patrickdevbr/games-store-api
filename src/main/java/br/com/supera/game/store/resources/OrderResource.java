package br.com.supera.game.store.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.OrderItemRequest;
import br.com.supera.game.store.entities.Order;
import br.com.supera.game.store.entities.OrderItem;
import br.com.supera.game.store.entities.Product;
import br.com.supera.game.store.services.OrderService;
import br.com.supera.game.store.services.ProductService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody List<OrderItemRequest> items) {
		Order order = new Order();
		order.setMoment(Instant.now());
		for (OrderItemRequest itemRequest : items) {
			Product p = productService.findById(itemRequest.getProductId());
			order.getItems().add(new OrderItem(null, p, itemRequest.getQuantity(), itemRequest.getPrice()));
		}
		order = service.insert(order);
		return ResponseEntity.ok(order);
	}
}
