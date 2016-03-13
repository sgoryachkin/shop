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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sego.shop.model.order.OrderItem;
import com.sego.shop.model.order.OrderItem_;
import com.sego.shop.model.order.PermanentState;
import com.sego.shop.model.order.SalesOrder;
import com.sego.shop.model.order.SalesOrder_;

/**
 * Session Bean implementation class OrderService
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OrderServiceImpl implements OrderService {

	@PersistenceContext(unitName = "shopPu")
	private EntityManager em;

	@Override
	public SalesOrder createSalesOrder() {
		SalesOrder order = new SalesOrder();
		em.persist(order);
		return order;
	}

	@Override
	public OrderItem addTemporaryOrderItem(Long salesOrderId) {
		OrderItem orderItem = new OrderItem();
		orderItem.setSalsOrderId(salesOrderId);
		em.persist(orderItem);
		return orderItem;
	}

	@Override
	public OrderItem getTemporaryOrderItemForEdit(Long salesOrderId, Long orderItemId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderItem> cq = cb.createQuery(OrderItem.class);
		Root<OrderItem> root = cq.from(OrderItem.class);

		Predicate salesOrderPredicate = cb.equal(root.get(OrderItem_.salsOrderId), salesOrderId);
		Predicate unsavedPredicate = cb.equal(root.get(OrderItem_.permanentState), PermanentState.UNSAVED);
		Predicate orderItemIdPredicate = cb.equal(root.get(OrderItem_.id), orderItemId);

		cq = cq.where(cb.and(salesOrderPredicate, unsavedPredicate, orderItemIdPredicate));
		List<OrderItem> result = em.createQuery(cq).getResultList();
		if (result.isEmpty()) {
			return createTemporaryOrderItemForEditFromSaved(salesOrderId, orderItemId, PermanentState.SAVED);
		} else {
			return result.get(0);
		}
	}

	private OrderItem createTemporaryOrderItemForEditFromSaved(Long salesOrderId, Long orderItemId,
			PermanentState permanentState) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderItem> cq = cb.createQuery(OrderItem.class);
		Root<OrderItem> root = cq.from(OrderItem.class);

		Predicate salesOrderPredicate = cb.equal(root.get(OrderItem_.salsOrderId), salesOrderId);
		Predicate unsavedPredicate = cb.equal(root.get(OrderItem_.permanentState), permanentState);
		Predicate orderItemIdPredicate = cb.equal(root.get(OrderItem_.id), orderItemId);

		cq = cq.where(cb.and(salesOrderPredicate, unsavedPredicate, orderItemIdPredicate));
		List<OrderItem> result = em.createQuery(cq).getResultList();
		if (result.isEmpty()) {
			return createTemporaryOrderItemForEditFromSaved(salesOrderId, orderItemId, PermanentState.SUBMITTED);
		} else {
			OrderItem oi = result.get(0);
			return createUnsavedClone(oi);
		}
	}

	private OrderItem createUnsavedClone(OrderItem orderItem) {
		em.detach(orderItem);
		orderItem.setPermanentState(PermanentState.UNSAVED);
		em.persist(orderItem);
		return orderItem;
	}
	
	private SalesOrder createUnsavedClone(SalesOrder salesOrder) {
		em.detach(salesOrder);
		salesOrder.setPermanentState(PermanentState.UNSAVED);
		em.persist(salesOrder);
		return salesOrder;
	}

	@Override
	public SalesOrder getSalesOrder(Long id) {
		return em.getReference(SalesOrder.class, id);
	}

	@Override
	public List<OrderItem> getTemporaryOrderItems(Long salesOrderId, OrderItem parentOrderItem) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderItem> cq = cb.createQuery(OrderItem.class);
		Root<OrderItem> root = cq.from(OrderItem.class);

		Expression<Boolean> exp1 = cb.equal(root.get(OrderItem_.salsOrderId), salesOrderId);
		Expression<Boolean> exp2 = cb.equal(root.get(OrderItem_.permanentState), PermanentState.UNSAVED);

		cq = cq.where(cb.and(exp1, exp2));
		return em.createQuery(cq).getResultList();
	}

	@Override
	public List<OrderItem> getOrderItems(Long salesOrderId, OrderItem parentOrderItem) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderItem> cq = cb.createQuery(OrderItem.class);
		Root<OrderItem> root = cq.from(OrderItem.class);

		Expression<Boolean> exp1 = cb.equal(root.get(OrderItem_.salsOrderId), salesOrderId);
		Expression<Boolean> exp2 = cb.notEqual(root.get(OrderItem_.permanentState), PermanentState.UNSAVED);

		cq = cq.where(cb.and(exp1, exp2));
		return em.createQuery(cq).getResultList();
	}

	@Override
	public void save(Long salesOrderId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<OrderItem> cu = cb.createCriteriaUpdate(OrderItem.class);
		Root<OrderItem> root = cu.from(OrderItem.class);
		cu.set(OrderItem_.permanentState, PermanentState.SAVED)
				.where(cb.equal(root.get(OrderItem_.salsOrderId), salesOrderId));
		Query query = em.createQuery(cu);
		query.executeUpdate();
		//salesOrder.setPermanentState(PermanentState.SAVED);
	}

	@Override
	public void submit(Long salesOrderId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<OrderItem> cu = cb.createCriteriaUpdate(OrderItem.class);
		Root<OrderItem> root = cu.from(OrderItem.class);
		cu.set(OrderItem_.permanentState, PermanentState.SUBMITTED)
				.where(cb.equal(root.get(OrderItem_.salsOrderId), salesOrderId));
		Query query = em.createQuery(cu);
		query.executeUpdate();
		//salesOrder.setPermanentState(PermanentState.SUBMITTED);
	}

	@Override
	public SalesOrder getTemporarySalesOrderForEdit(Long salesOrderId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SalesOrder> cq = cb.createQuery(SalesOrder.class);
		Root<SalesOrder> root = cq.from(SalesOrder.class);

		Predicate unsavedPredicate = cb.equal(root.get(SalesOrder_.permanentState), PermanentState.UNSAVED);
		Predicate salesOrderIdPredicate = cb.equal(root.get(SalesOrder_.id), salesOrderId);

		cq = cq.where(cb.and(unsavedPredicate, salesOrderIdPredicate));
		List<SalesOrder> result = em.createQuery(cq).getResultList();
		if (result.isEmpty()) {
			return getTemporarySalesOrderForEdit(salesOrderId, PermanentState.SAVED);
		} else {
			return result.get(0);
		}
	}

	private SalesOrder getTemporarySalesOrderForEdit(Long salesOrderId, PermanentState permanentState) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SalesOrder> cq = cb.createQuery(SalesOrder.class);
		Root<SalesOrder> root = cq.from(SalesOrder.class);

		Predicate unsavedPredicate = cb.equal(root.get(SalesOrder_.permanentState), permanentState);
		Predicate salesOrderIdPredicate = cb.equal(root.get(SalesOrder_.id), salesOrderId);

		cq = cq.where(cb.and(unsavedPredicate, salesOrderIdPredicate));
		List<SalesOrder> result = em.createQuery(cq).getResultList();
		if (result.isEmpty()) {
			return getTemporarySalesOrderForEdit(salesOrderId, PermanentState.SUBMITTED);
		} else {
			SalesOrder salesOrder = result.get(0);
			return createUnsavedClone(salesOrder);
		}
	}

}
