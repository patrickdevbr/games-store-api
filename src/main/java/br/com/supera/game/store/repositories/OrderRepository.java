package br.com.supera.game.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supera.game.store.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
