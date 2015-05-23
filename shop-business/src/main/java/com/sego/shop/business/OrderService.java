package com.sego.shop.business;

import com.sego.shop.model.order.SalesOrder;



public interface OrderService {
	
	SalesOrder createOrder();
	
	void save(Long id);

}
