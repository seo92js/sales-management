package com.seojs.salesmanagement.domain.product;

import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.orderproduct.OrderProduct;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String description;
    private Integer price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    @OneToMany(mappedBy = "product")
//    private List<OrderProduct> orderProducts = new ArrayList<>();

    //연관관계
    public void addOrderProducts(OrderProduct orderProducts) {
        //this.orderProducts.add(orderProducts);
        this.quantity -= orderProducts.getQuantity();
    }

    public void update(Integer quantity, Integer price) {
        this.quantity = quantity;
        this.price = price;
    }

    @Builder
    public Product(String productName, String description, Integer price, Integer quantity, Category category) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
}
