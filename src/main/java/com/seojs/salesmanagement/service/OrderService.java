package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.customer.CustomerRepository;
import com.seojs.salesmanagement.domain.orders.OrderStatus;
import com.seojs.salesmanagement.domain.orders.Orders;
import com.seojs.salesmanagement.domain.orders.OrdersRepository;
import com.seojs.salesmanagement.domain.orders.dto.OrdersResponseDto;
import com.seojs.salesmanagement.domain.payment.PayMethod;
import com.seojs.salesmanagement.domain.payment.Payment;
import com.seojs.salesmanagement.domain.product.ProductRepository;
import com.seojs.salesmanagement.domain.shipment.ShipmentStatus;
import com.seojs.salesmanagement.exception.OrderNotFoundEx;
import com.seojs.salesmanagement.exception.OrderStatusEx;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrdersResponseDto findById(Long id) {
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new OrderNotFoundEx("주문이 없습니다. id = " + id));

        return new OrdersResponseDto(orders);
    }

    @Transactional
    public List<OrdersResponseDto> findByCustomerId(Long id) {
        return ordersRepository.findByCustomerId(id).stream()
                .map(OrdersResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<OrdersResponseDto> findByCustomerIdAndOrderStatus(Long id, OrderStatus orderStatus) {
        return ordersRepository.findByCustomerIdAndOrderStatus(id, orderStatus).stream()
                .map(OrdersResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateCanceled(Long id) {
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new OrderNotFoundEx("주문이 없습니다. id = " + id));

        checkOrderStatus(orders);

        orders.updateStatus(OrderStatus.CANCELED);
    }

    @Transactional
    public void updateOrdered(Long id, PayMethod payMethod) {
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new OrderNotFoundEx("주문이 없습니다. id = " + id));

        Payment payment = Payment.builder()
                .amount(orders.getTotalPrice())
                .payMethod(payMethod)
                .build();

        orders.setPayment(payment);
        orders.updateStatus(OrderStatus.ORDERED);
    }

    @Transactional
    public void updateShipment(Long id, ShipmentStatus shipmentStatus) {
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new OrderNotFoundEx("주문이 없습니다. id = " + id));

        orders.updateShipment(shipmentStatus);
    }

    private void checkOrderStatus(Orders orders) {
        if (orders.getOrderStatus() == OrderStatus.DELIVERED) {
            throw new OrderStatusEx("이미 배송이 시작되었습니다.");
        }
    }
}
