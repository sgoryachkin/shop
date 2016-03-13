package com.sego.shop;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sego.shop.business.OrderService;
import com.sego.shop.model.AbstractItem;
import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.PermanentState;
import com.sego.shop.model.order.SalesOrder;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class OrderServiceImplTest {

	@Inject
	private OrderService orderService;
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addPackage(OrderService.class.getPackage())
				.addPackages(true, AbstractItem.class.getPackage())
				.addAsResource("META-INF/persistence.xml");
	}

	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void createSalesOrdr() {
		SalesOrder order = orderService.createSalesOrder();
		//SalesOrder order2 = orderService.getSalesOrder(order.getId());
		//Assert.assertEquals(order.getId(), order2.getId());
	}

	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void save() {
		SalesOrder order = orderService.createSalesOrder();
		for (int i = 0; i < 30; i++) {
			orderService.addTemporaryOrderItem(order.getId());
		}
		List<OrderItem> list = orderService.getTemporaryOrderItems(order.getId(), null);
		Assert.assertEquals(30, list.size());
		orderService.save(order.getId());
		list = orderService.getTemporaryOrderItems(order.getId(), null);
		Assert.assertEquals(0, list.size());
		list = orderService.getOrderItems(order.getId(), null);
		Assert.assertEquals(30, list.size());
		//SalesOrder salesOrder = orderService.getSalesOrder(order.getId());
		//Assert.assertEquals(salesOrder.getPermanentState(), PermanentState.SAVED);
	}

}
