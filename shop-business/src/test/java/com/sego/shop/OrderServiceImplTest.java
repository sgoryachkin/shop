package com.sego.shop;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.apache.openejb.junit.jee.EJBContainerRunner;
//import org.apache.openejb.junit.jee.transaction.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sego.shop.business.OrderService;
import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.SalesOrder;

//@RunWith(EJBContainerRunner.class)
public class OrderServiceImplTest {
/*
	@EJB
	private OrderService orderService;
	
	@PersistenceContext(unitName="shopPu")
	private EntityManager em;

	//@Test
	//@Transaction(rollback = true)
	public void create1() {
		SalesOrder order = orderService.createSalesOrder();
		System.out.println(order.getId());
	}

	@Test
	//@Transaction(rollback = true)
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
	}*/

}
