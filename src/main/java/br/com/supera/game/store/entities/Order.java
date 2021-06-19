package br.com.supera.game.store.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private Instant moment;
	private BigDecimal shippingFee;

	@OneToMany(mappedBy = "id.order")
	private List<OrderItem> items = new ArrayList<>();

	public Order() {
	}

	public Order(Long id, Instant moment, BigDecimal shippingValue, List<OrderItem> items) {
		this.id = id;
		this.moment = moment;
		this.shippingFee = shippingValue;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public BigDecimal getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public BigDecimal subtotal() {
		// @formatter:off
		BigDecimal itemsSubTotal = items.stream()
				.map(i -> i.subTotal())
				.reduce(BigDecimal.valueOf(0.0), 
						(x, y) -> x.add(y));
		// @formatter:on
		return itemsSubTotal;
	}

	public BigDecimal total() {
		return subtotal().add(shippingFee);
	}

}
