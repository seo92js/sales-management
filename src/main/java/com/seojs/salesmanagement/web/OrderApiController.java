package com.seojs.salesmanagement.web;

import com.seojs.salesmanagement.domain.orders.OrderStatus;
import com.seojs.salesmanagement.domain.orders.dto.OrdersResponseDto;
import com.seojs.salesmanagement.domain.payment.PayMethod;
import com.seojs.salesmanagement.domain.shipment.ShipmentStatus;
import com.seojs.salesmanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;

    @GetMapping("/api/v1/orders/{id}")
    public OrdersResponseDto findById(@PathVariable(name = "id") Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/api/v1/orders/customer/{id}")
    public List<OrdersResponseDto> findByCustomerId(@PathVariable(name = "id") Long id) {
        return orderService.findByCustomerId(id);
    }

    @GetMapping("/api/v1/orders/customer/{id}/status/{status}")
    public List<OrdersResponseDto> findByCustomerIdAndOrderStatus(@PathVariable(name = "id") Long id, @PathVariable(name = "status") OrderStatus status) {
        return orderService.findByCustomerIdAndOrderStatus(id, status);
    }

    @PatchMapping("/api/v1/orders/cancel/{id}")
    public void updateCanceled(@PathVariable(name = "id") Long id) {
        orderService.updateCanceled(id);
    }

    @PatchMapping("/api/v1/orders/{id}/payment/{payMethod}")
    public void updateOrdered(@PathVariable(name = "id") Long id, @PathVariable(name = "payMethod") PayMethod payMethod) {
        orderService.updateOrdered(id, payMethod);
    }

    @PatchMapping("/api/v1/orders/{id}/shipment/{shipment}")
    public void updateOrdered(@PathVariable(name = "id") Long id, @PathVariable(name = "shipment") ShipmentStatus shipmentStatus) {
        orderService.updateShipment(id, shipmentStatus);
    }
}
