package br.com.supera.game.store.dto;

import java.math.BigDecimal;

import br.com.supera.game.store.entities.Product;

public class ProductDTO {

	private Long id;
	private String name;
	private BigDecimal price;
	private Short score;
	private String image;

	public ProductDTO(Product p) {
		id = p.getId();
		name = p.getName();
		price = p.getPrice();
		score = p.getScore();
		image = p.getImage();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Short getScore() {
		return score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
