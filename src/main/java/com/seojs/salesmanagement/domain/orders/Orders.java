package com.seojs.salesmanagement.domain.orders;

import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.orderproduct.OrderProduct;
import com.seojs.salesmanagement.domain.payment.Payment;
import com.seojs.salesmanagement.domain.shipment.Shipment;
import com.seojs.salesmanagement.domain.shipment.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Orders {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Integer totalPrice;
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    //연관관계
    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.addOrder(this);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        this.orderStatus = OrderStatus.ORDERED;
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void updateShipment(ShipmentStatus shipmentStatus) {
        this.shipment.update(shipmentStatus);
        this.orderStatus = OrderStatus.DELIVERED;
    }

    public void addOrderProducts(OrderProduct orderProducts) {
        this.orderProducts.add(orderProducts);
        this.quantity += orderProducts.getQuantity();
        this.totalPrice += orderProducts.getProduct().getPrice() * orderProducts.getQuantity();
    }

    public Orders() {
        this.quantity = 0;
        this.totalPrice = 0;
        this.orderStatus = OrderStatus.BASKET;
        this.shipment = new Shipment();
    }
}
