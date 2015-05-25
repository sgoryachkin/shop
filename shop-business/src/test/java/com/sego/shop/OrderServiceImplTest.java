package com.sego.shop;

import javax.ejb.EJB;

import org.apache.openejb.junit.jee.EJBContainerRunner;
import org.apache.openejb.junit.jee.transaction.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sego.shop.business.OrderService;
import com.sego.shop.model.order.SalesOrder;

@RunWith(EJBContainerRunner.class)
public class OrderServiceImplTest {

	@EJB
	private OrderService orderService;

	@Test
	@Transaction(rollback = true)
	public void create1() {
		SalesOrder order = orderService.createOrder();
		System.out.println(order.getId());
	}

	@Test
	@Transaction(rollback = true)
	public void create2() {
		SalesOrder order = orderService.createOrder();
		System.out.println(order.getId());
	}

}
