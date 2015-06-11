package com.sego.shop;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sego.shop.business.OrderService;

@RunWith(Arquillian.class)
public class CopyOfOrderServiceImplTest {

	@EJB
	private OrderService orderService;
	
	@BeforeClass
	public static void setup() {
		//EJBContainer container = EJBContainer.createEJBContainer();
		//Context ctx = container.getContext();
	}


	@Test
	public void create2() throws NamingException {
		orderService.createSalesOrder();
		
	}
	/*
	//@Deployment
	//public static JavaArchive createDeployment() {
	//    JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
	        .addClass(OrderServiceImpl.class).addPackage("com.sego.shop");
	        //.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	    System.out.println(jar.toString(true));
	    return jar;
	}
*/
}
