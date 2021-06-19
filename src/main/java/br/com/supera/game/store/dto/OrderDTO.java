package br.com.supera.game.store.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.supera.game.store.entities.Order;
import br.com.supera.game.store.entities.OrderItem;

public class OrderDTO {

	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;	
	private BigDecimal shippingFee;	
	private BigDecimal subtotal;	
	private BigDecimal total;	
	private List<OrderItem> items = new ArrayList<>();

	public OrderDTO(Order o) {
		id = o.getId();
		moment = o.getMoment();
		shippingFee = o.getShippingFee();
		subtotal = o.subtotal();
		total = o.total();
		items = List.copyOf(o.getItems());
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

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<OrderItem> getItems() {
		return items;
	}

}
