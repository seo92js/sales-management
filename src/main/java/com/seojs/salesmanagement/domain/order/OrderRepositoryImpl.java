package com.seojs.salesmanagement.domain.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.seojs.salesmanagement.domain.order.QOrder.order;

public class OrderRepositoryImpl implements OrderRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Order> findByCustomerId(Long id) {
        return queryFactory.selectFrom(order)
                .where(order.customer.id.eq(id))
                .fetch();
    }
}
