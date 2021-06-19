package br.com.supera.game.store.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.supera.game.store.entities.OrderItem;
import br.com.supera.game.store.requests.OrderItemRequest;
import br.com.supera.game.store.services.ProductService;

@Component
public class OrderItemFactory {

	@Autowired
	private ProductService productService;

	public OrderItem createFromRequest(OrderItemRequest req) {
		OrderItem item = new OrderItem();
		item.setOrder(null);
		item.setPrice(req.getPrice());
		item.setQuantity(req.getQuantity());
		item.setProduct(productService.findById(req.getProductId()));

		return item;
	}
}
