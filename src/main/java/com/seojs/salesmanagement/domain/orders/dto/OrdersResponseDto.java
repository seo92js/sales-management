package com.seojs.salesmanagement.domain.orders.dto;

import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.orderproduct.OrderProduct;
import com.seojs.salesmanagement.domain.orders.Orders;
import com.seojs.salesmanagement.domain.payment.Payment;
import com.seojs.salesmanagement.domain.product.Product;
import com.seojs.salesmanagement.domain.orders.OrderStatus;
import com.seojs.salesmanagement.domain.shipment.Shipment;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrdersResponseDto {
    private Integer quantity;
    private Integer totalPrice;
    private OrderStatus orderStatus;
    private Customer customer;
    private List<OrderProduct> orderProducts = new ArrayList<>();
    private Payment payment;
    private Shipment shipment;

    public OrdersResponseDto(Orders orders) {
        this.quantity = orders.getQuantity();
        this.totalPrice = orders.getTotalPrice();
        this.orderStatus = orders.getOrderStatus();
        this.customer = orders.getCustomer();
        this.orderProducts = orders.getOrderProducts();
        this.payment = orders.getPayment();
        this.shipment = orders.getShipment();
    }
}
