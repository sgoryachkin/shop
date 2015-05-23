package com.sego.shop;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.sego.shop.business.OrderService;
import com.sego.shop.business.OrderServiceImpl;
import com.sego.shop.model.order.SalesOrder;

public class OrderServiceImplTest {

	OrderService orderService;

	@Before
	public void bootContainer() throws NamingException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.LocalInitialContextFactory");
		Context context = new InitialContext(props);
		orderService = (OrderService) context.lookup(OrderServiceImpl.class
				.getSimpleName() + "Local");
	}

	@Test
	public void test() {
		SalesOrder order = orderService.createOrder();
		System.out.println(order.getId());
	}

}
