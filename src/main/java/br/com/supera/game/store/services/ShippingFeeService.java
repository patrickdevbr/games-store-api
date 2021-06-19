package br.com.supera.game.store.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.supera.game.store.entities.Order;

@Service
public interface ShippingFeeService {

	BigDecimal calculate(Order order);
}
