package com.sego.shop.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order {
	
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
