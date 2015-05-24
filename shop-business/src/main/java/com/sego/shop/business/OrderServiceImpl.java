package com.sego.shop.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.OrderItem_;
import com.sego.shop.model.order.SalesOrder;

/**
 * Session Bean implementation class OrderService
 */
@Stateless
@Transactional(value = TxType.MANDATORY)
public class OrderServiceImpl implements OrderService {

	@PersistenceContext(unitName="shopPu")
	private EntityManager em;
	
	@Override
	public SalesOrder createOrder() {
		SalesOrder order = new SalesOrder();
		em.persist(order);
		return order;
	}
	
	@Override
	public SalesOrder getOrder(Long id) {
		return em.find(SalesOrder.class, id);
	}
	
	public List<OrderItem> getChild(SalesOrder order, OrderItem parentOrderItem) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderItem> cq = cb.createQuery(OrderItem.class);
		Root<OrderItem> root = cq.from(OrderItem.class);
	
		cq = cq.where(cb.equal(root.get(OrderItem_.order), order));
		return em.createQuery(cq).getResultList();
	}

	@Override
	public void save(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<OrderItem> cu = cb.createCriteriaUpdate(OrderItem.class);
		Root<OrderItem> root = cu.from(OrderItem.class);
		
		cu.set(OrderItem_.state, 1).where(cb.equal(root.get(OrderItem_.state), 0));
		em.createQuery(cu);
	}


}
