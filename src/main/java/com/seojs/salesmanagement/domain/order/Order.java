package com.seojs.salesmanagement.domain.order;

import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.payment.Payment;
import com.seojs.salesmanagement.domain.product.Product;
import com.seojs.salesmanagement.domain.product.ProductStatus;
import com.seojs.salesmanagement.domain.shipment.Shipment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Integer totalPrice;
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    //shipment 변경 때문 ?
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    //연관관계
    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.addOrder(this);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Builder
    public Order(Integer quantity, Integer totalPrice) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.productStatus = ProductStatus.ORDERED;
        this.shipment = new Shipment();
    }
}
