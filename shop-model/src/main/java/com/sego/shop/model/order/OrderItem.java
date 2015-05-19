package com.sego.shop.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sego.shop.model.AbstractItem;

@Entity
public class OrderItem extends AbstractItem {

	@Id
	private Long id;

	@ManyToOne
	private Order order;

	private int state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int isState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
