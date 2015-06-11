package com.sego.shop.business;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.OrderItem_;
import com.sego.shop.model.order.SalesOrder;



public interface OrderService {
	
	SalesOrder createSalesOrder();
	
	OrderItem addTemporaryOrderItem(SalesOrder salesOrder);
	
	List<OrderItem> getTemporaryOrderItems(SalesOrder salesOrder, OrderItem parentOrderItem);
	
	SalesOrder getSalesOrder(Long id);
	
	void save(SalesOrder salesOrder);

}
