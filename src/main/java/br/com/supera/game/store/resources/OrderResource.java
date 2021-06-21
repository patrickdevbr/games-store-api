package br.com.supera.game.store.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.dto.OrderDTO;
import br.com.supera.game.store.entities.Order;
import br.com.supera.game.store.entities.OrderItem;
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

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(new OrderDTO(service.findById(id)));
	}

	@GetMapping
	public ResponseEntity<Page<OrderDTO>> findAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {

		Page<Order> orders = service.findAll(PageRequest.of(page, limit));
		Page<OrderDTO> dto = orders.map(o -> new OrderDTO(o));
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody List<OrderItemRequest> items) {
		Order order = new Order();
		order.setMoment(Instant.now());
		for (OrderItemRequest itemRequest : items)
			order.getItems().add(itemFactory.createFromRequest(itemRequest));

		order = service.insert(order);
		return ResponseEntity.ok(new OrderDTO(order));
	}

	@PostMapping(value = "{id}/items")
	public ResponseEntity<OrderDTO> addItem(@PathVariable Long id, @RequestBody OrderItemRequest item) {
		OrderItem oi = itemFactory.createFromRequest(item);
		Order order = service.findById(id);

		service.addItem(order, oi);
		return ResponseEntity.ok(new OrderDTO(order));
	}

	@DeleteMapping(value = "{id}/items")
	public ResponseEntity<OrderDTO> removeItem(@PathVariable Long id, @RequestBody OrderItemRequest item) {
		Order order = service.findById(id);
		service.removeItem(order, itemFactory.createFromRequest(item));
		return ResponseEntity.ok(new OrderDTO(order));
	}
}
