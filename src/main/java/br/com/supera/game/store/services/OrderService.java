package br.com.supera.game.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Page<Order> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

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
		item = itemRepository.save(processItemAddition(order, item));
		order.setShippingFee(shippingFeeService.calculate(order));
		return repository.save(order);
	}

	public Order addItems(Order order, List<OrderItem> items) {
		items.forEach(item -> {
			item.setOrder(order);
			itemRepository.save(processItemAddition(order, item));
		});
		order.setShippingFee(shippingFeeService.calculate(order));
		return repository.save(order);
	}

	public Order removeItem(Order order, OrderItem item) {
		item.setOrder(order);
		itemRepository.delete(item);
		order.getItems().remove(item);
		return order;
	}

	private OrderItem processItemAddition(Order order, OrderItem item) {
		int index = order.getItems().indexOf(item); // return -1 if does not exist
		if (index != -1) {
			OrderItem item2 = order.getItems().get(index);
			Integer newQuantity = item.getQuantity() + item2.getQuantity();
			item2.setQuantity(newQuantity);
			return item2;
		} else {
			order.getItems().add(item);
			return item;
		}
	}

}
