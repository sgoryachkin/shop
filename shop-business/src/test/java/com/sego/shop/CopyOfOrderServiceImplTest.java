package com.sego.shop;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sego.shop.business.OrderService;
import com.sego.shop.model.AbstractItem;
import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.SalesOrder;

@RunWith(Arquillian.class)
public class CopyOfOrderServiceImplTest {

	@Inject
	private OrderService orderService;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addPackage(OrderService.class.getPackage())
				.addPackages(true, AbstractItem.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
