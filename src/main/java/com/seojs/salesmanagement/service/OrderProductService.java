package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.customer.CustomerRepository;
import com.seojs.salesmanagement.domain.orderproduct.OrderProduct;
import com.seojs.salesmanagement.domain.orderproduct.OrderProductRepository;
import com.seojs.salesmanagement.domain.orderproduct.dto.OrderProductSaveDto;
import com.seojs.salesmanagement.domain.orders.OrderStatus;
import com.seojs.salesmanagement.domain.orders.Orders;
import com.seojs.salesmanagement.domain.orders.OrdersRepository;
import com.seojs.salesmanagement.domain.product.Product;
import com.seojs.salesmanagement.domain.product.ProductRepository;
import com.seojs.salesmanagement.exception.CustomerNotFoundEx;
import com.seojs.salesmanagement.exception.NotInStockEx;
import com.seojs.salesmanagement.exception.ProductNotFoundEx;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final CustomerRepository customerRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long save(OrderProductSaveDto orderProductSaveDto) {
        /**
         * basket 상태의 Orders가 없으면 생성
         */

        Customer customer = customerRepository.findById(orderProductSaveDto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundEx("고객이 없습니다. id = " + orderProductSaveDto.getCustomerId()));
        Orders orders;

        if (customer.getOrders().stream().noneMatch(m -> m.getOrderStatus().equals(OrderStatus.BASKET))) {
            orders = new Orders();
            orders.setCustomer(customer);
            ordersRepository.save(orders);
        } else {
            orders = customer.getOrders().stream()
                    .filter(order -> order.getOrderStatus() == OrderStatus.BASKET)
                    .findFirst().get();
        }

        Product product = productRepository.findById(orderProductSaveDto.getProductId()).orElseThrow(() -> new ProductNotFoundEx("제품이 없습니다. id = " + orderProductSaveDto.getProductId()));

        //수량 체크
        checkProductQuantity(product, orderProductSaveDto.getQuantity());

        OrderProduct orderProduct = OrderProduct.builder()
                .orders(orders)
                .product(product)
                .quantity(orderProductSaveDto.getQuantity())
                .build();

        orders.addOrderProducts(orderProduct);
        product.addOrderProducts(orderProduct);

        orderProductRepository.save(orderProduct);

        return orders.getId();
    }

    private void checkProductQuantity(Product product, Integer orderQuantity) {
        if (product.getQuantity() < orderQuantity) {
            throw new NotInStockEx("재고가 부족합니다. 남은 수량 = " + product.getQuantity());
        }
    }
}
