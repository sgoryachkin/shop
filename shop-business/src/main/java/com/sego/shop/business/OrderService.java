package com.sego.shop.business;

import com.sego.shop.model.order.SalesOrder;



public interface OrderService {
	
	SalesOrder createOrder();
	
	SalesOrder getOrder(Long id);
	
	void save(Long id);

}
