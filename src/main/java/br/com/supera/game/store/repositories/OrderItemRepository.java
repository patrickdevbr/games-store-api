package br.com.supera.game.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supera.game.store.entities.OrderItem;
import br.com.supera.game.store.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{	
}
