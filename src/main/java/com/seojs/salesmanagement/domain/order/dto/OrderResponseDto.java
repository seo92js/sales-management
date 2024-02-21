package com.seojs.salesmanagement.domain.order.dto;

import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.order.Order;
import com.seojs.salesmanagement.domain.payment.Payment;
import com.seojs.salesmanagement.domain.product.Product;
import com.seojs.salesmanagement.domain.product.ProductStatus;
import com.seojs.salesmanagement.domain.shipment.Shipment;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponseDto {
    private Integer quantity;
    private Integer totalPrice;
    private ProductStatus productStatus;
    private Customer customer;
    private List<Product> products = new ArrayList<>();
    private Payment payment;
    private Shipment shipment;

    public OrderResponseDto(Order order) {
        this.quantity = order.getQuantity();
        this.totalPrice = order.getTotalPrice();
        this.productStatus = order.getProductStatus();
        this.customer = order.getCustomer();
        this.products = order.getProducts();
        this.payment = order.getPayment();
        this.shipment = order.getShipment();
    }
}
