package com.seojs.salesmanagement.domain.orders;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.seojs.salesmanagement.domain.orders.QOrders.orders;

public class OrdersRepositoryImpl implements OrdersRepositoryCustom {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public OrdersRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Orders> findByCustomerId(Long id) {
        return queryFactory.selectFrom(orders)
                .where(orders.customer.id.eq(id))
                .fetch();
    }

    public List<Orders> findByCustomerIdAndOrderStatus(Long id, OrderStatus orderStatus) {
        return queryFactory.selectFrom(orders)
                .where(orders.customer.id.eq(id).and(orders.orderStatus.eq(orderStatus)))
                .fetch();
    }
}
