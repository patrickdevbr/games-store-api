package br.com.supera.game.store.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Product product;
	private Integer quantity;
	private BigDecimal price;

	public OrderItem() {
	}

	public OrderItem(Product product, Integer quantity, BigDecimal price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
