package com.sego.shop;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.apache.openejb.junit.jee.EJBContainerRunner;
//import org.apache.openejb.junit.jee.transaction.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sego.shop.business.OrderService;
import com.sego.shop.model.AbstractItem;
import com.sego.shop.model.order.OrderItem;
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
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void createSalesOrdr() {
		SalesOrder order = orderService.createSalesOrder();
		order = orderService.getSalesOrder(order.getId());
	}

	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void save() {
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
