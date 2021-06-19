package br.com.supera.game.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supera.game.store.entities.Order;
import br.com.supera.game.store.entities.OrderItem;
import br.com.supera.game.store.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ShippingFeeService shippingFeeService;

	public Order insert(Order order) {
		order.setShippingFee(shippingFeeService.calculate(order));
		return repository.save(order);
	}

	public Order addItem(Order order, OrderItem item) {
		order.getItems().add(item);
		order.setShippingFee(shippingFeeService.calculate(order));
		return repository.save(order);
	}

	public Order removeItem(Order order, OrderItem item) {
		order.getItems().remove(item);
		return repository.save(order);
	}
}
