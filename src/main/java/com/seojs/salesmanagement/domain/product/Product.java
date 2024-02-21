package com.seojs.salesmanagement.domain.product;

import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Integer price;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    //연관관계
    public void setOrder(Order order) {
        this.order = order;
        order.addProduct(this);
    }

    @Builder
    public Product(String productName, Integer price, Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }
}
