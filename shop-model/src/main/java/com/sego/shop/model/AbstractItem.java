package com.sego.shop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class AbstractItem {
	
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
