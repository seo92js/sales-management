package com.seojs.salesmanagement.domain.orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.orderproduct.OrderProduct;
import com.seojs.salesmanagement.domain.orders.OrderStatus;
import com.seojs.salesmanagement.domain.orders.Orders;
import com.seojs.salesmanagement.domain.payment.Payment;
import com.seojs.salesmanagement.domain.shipment.Shipment;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrdersResponseDto {
    private Integer quantity;
    private Integer totalPrice;
    private OrderStatus orderStatus;
    @JsonIgnore
    private List<OrderProduct> orderProducts = new ArrayList<>();
    @JsonIgnore
    private Customer customer;
    @JsonIgnore
    private Payment payment;
    @JsonIgnore
    private Shipment shipment;

    public OrdersResponseDto(Orders orders) {
        this.quantity = orders.getQuantity();
        this.totalPrice = orders.getTotalPrice();
        this.orderStatus = orders.getOrderStatus();
        this.orderProducts = orders.getOrderProducts();
        this.customer = orders.getCustomer();
        this.payment = orders.getPayment();
        this.shipment = orders.getShipment();
    }
}
