package br.com.supera.game.store.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private Instant moment;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.order")
	private List<OrderItem> items = new ArrayList<>();

	public Order() {
	}

	public Order(Long id, Instant moment, List<OrderItem> items) {
		this.id = id;
		this.moment = moment;
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

	public List<OrderItem> getItems() {
		return items;
	}

}
