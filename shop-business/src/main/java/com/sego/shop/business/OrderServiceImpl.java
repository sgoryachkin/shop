package com.sego.shop.business;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.sego.shop.model.order.Order;
import com.sego.shop.model.order.OrderItem;

/**
 * Session Bean implementation class OrderService
 */
@Stateless
@Transactional(value = TxType.MANDATORY)
public class OrderServiceImpl implements OrderService {

	@Inject
	EntityManager em;

	@Override
	public Order createOrder() {
		Order order = new Order();
		em.persist(order);
		return order;
	}

	@Override
	public void save(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//CriteriaUpdate<OrderItem> criteria = cb.equal(x, y)  )createCriteriaUpdate(OrderItem.class);
		
		//entityManager.createQuery(Cri)
		
	}


}
