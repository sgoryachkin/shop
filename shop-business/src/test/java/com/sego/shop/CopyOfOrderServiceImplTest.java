package com.sego.shop;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sego.shop.business.OrderService;
import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.SalesOrder;


public class CopyOfOrderServiceImplTest {

	@EJB
	private OrderService orderService;
	
	@Before
	public void setup() throws NamingException {
		EJBContainer container = EJBContainer.createEJBContainer();
		Context ctx = container.getContext();
		orderService = (OrderService) ctx.lookup("java:global/classes/OrderServiceImpl");
	}
	
	@Test
	public void create2() {
		SalesOrder order = orderService.createSalesOrder();
		for (int i = 0; i < 30; i++) {
			orderService.addTemporaryOrderItem(order);
		}
		List<OrderItem> list = orderService.getTemporaryOrderItems(order, null);
		Assert.assertEquals(30, list.size());
		orderService.save(order);
		list = orderService.getTemporaryOrderItems(order, null);
		Assert.assertEquals(0, list.size());
		list = orderService.getOrderItems(order, null);
		Assert.assertEquals(30, list.size());
	}
}
