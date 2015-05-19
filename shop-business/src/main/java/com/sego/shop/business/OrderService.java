package com.sego.shop.business;

import com.sego.shop.model.order.Order;



public interface OrderService {
	
	Order createOrder();
	
	void save(Long id);

}
