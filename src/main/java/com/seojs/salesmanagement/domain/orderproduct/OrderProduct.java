package com.seojs.salesmanagement.domain.orderproduct;

import com.seojs.salesmanagement.domain.orders.Orders;
import com.seojs.salesmanagement.domain.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @Builder
    public OrderProduct(Orders orders, Product product, Integer quantity) {
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
    }
}
