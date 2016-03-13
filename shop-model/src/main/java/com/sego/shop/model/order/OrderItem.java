package com.sego.shop.model.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sego.shop.model.product.Product;

@Entity
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 8083623572996661549L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Id 
	private PermanentState permanentState = PermanentState.UNSAVED;

	@Column
	private Long salsOrderId;
	
	@Column
	private String name;
	
	@ManyToOne
	private Product product;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PermanentState getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(PermanentState permanentState) {
		this.permanentState = permanentState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSalsOrderId() {
		return salsOrderId;
	}

	public void setSalsOrderId(Long salsOrderId) {
		this.salsOrderId = salsOrderId;
	}


}
