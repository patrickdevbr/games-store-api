package br.com.supera.game.store.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.entities.Order;
import br.com.supera.game.store.factories.OrderItemFactory;
import br.com.supera.game.store.requests.OrderItemRequest;
import br.com.supera.game.store.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@Autowired
	private OrderItemFactory itemFactory;

	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody List<OrderItemRequest> items) {
		Order order = new Order();
		order.setMoment(Instant.now());
		for (OrderItemRequest itemRequest : items)
			order.getItems().add(itemFactory.createFromRequest(itemRequest));

		order = service.insert(order);
		return ResponseEntity.ok(order);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PostMapping(value = "{id}/items")
	public ResponseEntity<Order> addItem(@PathVariable Long id, @RequestBody OrderItemRequest item) {
		Order order = service.findById(id);
		service.addItem(order, itemFactory.createFromRequest(item));
		return ResponseEntity.ok(order);
	}
	
	@DeleteMapping(value = "{id}/items")
	public ResponseEntity<Order> removeItem(@PathVariable Long id, @RequestBody OrderItemRequest item) {
		Order order = service.findById(id);
		service.removeItem(order, itemFactory.createFromRequest(item));
		return ResponseEntity.ok(order);
	}
}
