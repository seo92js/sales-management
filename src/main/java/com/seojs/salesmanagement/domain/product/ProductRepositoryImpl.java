package com.seojs.salesmanagement.domain.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.seojs.salesmanagement.domain.product.QProduct.product;

public class ProductRepositoryImpl implements ProductRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return queryFactory
                .selectFrom(product)
                .where(product.category.name.eq(categoryName))
                .fetch();
    }
}
