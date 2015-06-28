package com.sego.shop.business;

import java.util.List;

import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.SalesOrder;



public interface OrderService {
	
	SalesOrder createSalesOrder();
	
	OrderItem addTemporaryOrderItem(SalesOrder salesOrder);
	
	List<OrderItem> getTemporaryOrderItems(SalesOrder salesOrder, OrderItem parentOrderItem);
	
	List<OrderItem> getOrderItems(SalesOrder order, OrderItem parentOrderItem);
	
	SalesOrder getSalesOrder(Long id);
	
	void save(SalesOrder salesOrder);

}
