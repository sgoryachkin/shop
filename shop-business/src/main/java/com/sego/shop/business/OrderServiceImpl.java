package com.sego.shop.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.OrderItem_;
import com.sego.shop.model.order.SalesOrder;

/**
 * Session Bean implementation class OrderService
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OrderServiceImpl implements OrderService {

	@PersistenceContext(unitName="shopPu")
	private EntityManager em;
	
	@Override
	public SalesOrder createSalesOrder() {
		SalesOrder order = new SalesOrder();
		em.persist(order);
		return order;
	}
	
	@Override
	public OrderItem addTemporaryOrderItem(SalesOrder salesOrder) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(salesOrder);
		em.persist(orderItem);
		return orderItem;
	}
	
	@Override
	public SalesOrder getSalesOrder(Long id) {
		return em.find(SalesOrder.class, id);
	}
	
	@Override
	public List<OrderItem> getTemporaryOrderItems(SalesOrder order, OrderItem parentOrderItem) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderItem> cq = cb.createQuery(OrderItem.class);
		Root<OrderItem> root = cq.from(OrderItem.class);
		
		Expression<Boolean> exp1 = cb.equal(root.get(OrderItem_.order), order);
		Expression<Boolean> exp2 = cb.equal(root.get(OrderItem_.permanent), false);
		
		cq = cq.where(cb.and(exp1, exp2));
		return em.createQuery(cq).getResultList();
	}
	
	@Override
	public List<OrderItem> getOrderItems(SalesOrder order, OrderItem parentOrderItem) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderItem> cq = cb.createQuery(OrderItem.class);
		Root<OrderItem> root = cq.from(OrderItem.class);
		
		Expression<Boolean> exp1 = cb.equal(root.get(OrderItem_.order), order);
		Expression<Boolean> exp2 = cb.equal(root.get(OrderItem_.permanent), true);
		
		cq = cq.where(cb.and(exp1, exp2));
		return em.createQuery(cq).getResultList();
	}


	@Override
	public void save(SalesOrder salesOrder) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<OrderItem> cu = cb.createCriteriaUpdate(OrderItem.class);
		Root<OrderItem> root = cu.from(OrderItem.class);
		cu.set(OrderItem_.permanent, true).where(cb.equal(root.get(OrderItem_.order), salesOrder));
		Query query = em.createQuery(cu);
		query.executeUpdate();
	}




}
