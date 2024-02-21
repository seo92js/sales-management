package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.order.Order;
import com.seojs.salesmanagement.domain.order.OrderRepository;
import com.seojs.salesmanagement.domain.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Long save(Order order) {
        return orderRepository.save(order).getId();
    }

    @Transactional
    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();

        return new OrderResponseDto(order);
    }

    @Transactional
    public List<OrderResponseDto> findByCustomerId(Long id) {
        return orderRepository.findByCustomerId(id).stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }
}
