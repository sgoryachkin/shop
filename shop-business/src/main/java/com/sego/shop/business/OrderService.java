package com.sego.shop.business;

import java.util.List;

import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.SalesOrder;



public interface OrderService {
	
	SalesOrder createSalesOrder();
	
	OrderItem addTemporaryOrderItem(Long salesOrderId);
	
	OrderItem getTemporaryOrderItemForEdit(Long salesOrderId, Long orderItemId);
	
	SalesOrder getTemporarySalesOrderForEdit(Long salesOrderId);
	
	List<OrderItem> getTemporaryOrderItems(Long salesOrderId, OrderItem parentOrderItem);
	
	List<OrderItem> getOrderItems(Long salesOrderId, OrderItem parentOrderItem);
	
	SalesOrder getSalesOrder(Long id);
	
	void save(Long salesOrderId);
	
	void submit(Long salesOrderId);

}
