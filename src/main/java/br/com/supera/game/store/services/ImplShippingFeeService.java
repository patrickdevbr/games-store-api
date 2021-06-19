package br.com.supera.game.store.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.supera.game.store.entities.Order;

@Service
public class ImplShippingFeeService implements ShippingFeeService {

	private final BigDecimal MIN_AMOUNT_FOR_FREE = BigDecimal.valueOf(250.00);
	private final BigDecimal FEE_FOR_PRODUCT = BigDecimal.valueOf(10.00);

	@Override
	public BigDecimal calculate(Order order) {
		BigDecimal fee = BigDecimal.valueOf(0.0);
		if (order.subtotal().doubleValue() >= MIN_AMOUNT_FOR_FREE.doubleValue())
			return fee;
		return FEE_FOR_PRODUCT.multiply(BigDecimal.valueOf(order.getItems().size()));
	}

}
