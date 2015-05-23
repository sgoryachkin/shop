package com.sego.shop.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sego.shop.model.AbstractItem;
import com.sego.shop.model.product.Product;

@Entity
public class OrderItem extends AbstractItem {

	@Id
	private Long id;

	@ManyToOne
	private SalesOrder order;
	
	@ManyToOne
	private Product product;

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
	
	public SalesOrder getOrder() {
		return order;
	}

	public void setOrder(SalesOrder order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
