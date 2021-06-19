package br.com.supera.game.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supera.game.store.entities.Order;
import br.com.supera.game.store.entities.OrderItem;
import br.com.supera.game.store.repositories.OrderItemRepository;
import br.com.supera.game.store.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private OrderItemRepository itemRepository;

	@Autowired
	private ShippingFeeService shippingFeeService;

	public Order insert(Order order) {
		order.setShippingFee(shippingFeeService.calculate(order));
		Order orderInserted = repository.save(order);
		order.getItems().forEach(i -> {
			i.setOrder(orderInserted);
			itemRepository.save(i);
		});

		return orderInserted;
	}

	public Order findById(Long id) {
		return repository.findById(id).get();
	}

	public Order addItem(Order order, OrderItem item) {
		item.setOrder(order);
		item = itemRepository.save(item);
		order.setShippingFee(shippingFeeService.calculate(order));
		return repository.save(order);
	}

	public Order removeItem(Order order, OrderItem item) {
		item.setOrder(order);
		itemRepository.delete(item);
		order.getItems().remove(item);
		return order;
	}
}
