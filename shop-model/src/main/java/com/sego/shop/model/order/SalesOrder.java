package com.sego.shop.model.order;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalesOrder implements Serializable {
	
	private static final long serialVersionUID = 6950087470497605038L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) 
	private Long id;
	
	@Id
	private PermanentState permanentState = PermanentState.UNSAVED;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PermanentState getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(PermanentState permanentState) {
		this.permanentState = permanentState;
	}

}
