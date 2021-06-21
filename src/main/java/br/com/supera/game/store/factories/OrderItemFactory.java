package br.com.supera.game.store.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.supera.game.store.entities.OrderItem;
import br.com.supera.game.store.entities.Product;
import br.com.supera.game.store.resources.requests.OrderItemRequest;
import br.com.supera.game.store.services.ProductService;

@Component
public class OrderItemFactory {

	@Autowired
	private ProductService productService;

	public OrderItem createFromRequest(OrderItemRequest req) {
		OrderItem item = new OrderItem();
		Product p = productService.findById(req.getProductId());
		
		item.setOrder(null);
		item.setPrice(req.getPrice() == null ? p.getPrice() : req.getPrice());
		item.setQuantity(req.getQuantity());
		item.setProduct(p);

		return item;
	}
}
