package com.seojs.salesmanagement.domain.shipment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.product.Product;
import com.seojs.salesmanagement.domain.product.QProduct;
import com.seojs.salesmanagement.domain.product.dto.ProductResponseDto;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.seojs.salesmanagement.domain.product.QProduct.product;

public class ShipmentRepositoryImpl implements ShipmentRepositoryCustom {
}