package com.sego.shop;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.sego.shop.business.OrderService;
import com.sego.shop.business.OrderServiceImpl;
import com.sego.shop.model.order.SalesOrder;

public class OrderServiceImplTest {

	private OrderService orderService;
		
	@Before
	public void bootContainer() throws NamingException {
		InitialContext ctx = new InitialContext();
		orderService = (OrderService) ctx.lookup(OrderServiceImpl.class
				.getSimpleName() + "Local");
	}

	@Test
	public void test() {
		SalesOrder order = orderService.createOrder();
		System.out.println(order.getId());
	}
	
	@Test
	public void test2() {
		SalesOrder order = orderService.createOrder();
		System.out.println(order.getId());
		SalesOrder order1 = orderService.getOrder(10l);
		System.out.println(order1.getId());
		
	}

}
