package com.sego.shop.model.product;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.sego.shop.model.AbstractItem;

/**
 * @author sego
 *
 */
@Entity
public class ProductItem extends AbstractItem {
	
	@Id
	private Long id;
	
	private String name;
	
	private Long productId;
	
	private Long parentId;

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	
}
