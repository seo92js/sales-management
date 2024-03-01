package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.customer.Role;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import com.seojs.salesmanagement.domain.orderproduct.dto.OrderProductSaveDto;
import com.seojs.salesmanagement.domain.orders.OrderStatus;
import com.seojs.salesmanagement.domain.orders.dto.OrdersResponseDto;
import com.seojs.salesmanagement.domain.payment.PayMethod;
import com.seojs.salesmanagement.domain.product.dto.ProductSaveDto;
import com.seojs.salesmanagement.domain.shipment.ShipmentStatus;
import com.seojs.salesmanagement.exception.OrderNotFoundEx;
import com.seojs.salesmanagement.exception.OrderStatusEx;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderProductService orderProductService;

    Long customerId;
    Long productId;
    Long categoryId;

    @BeforeEach
    void setUp() {
        String loginId = "loginId";
        String password = "password";
        String name = "seo";
        String email = "seojs@naver.com";
        String phoneNumber = "010-1111-1111";
        String address = "서울시 강서구 등촌동";
        Role role = Role.USER;

        CustomerSaveDto customerSaveDto = CustomerSaveDto.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address)
                .role(role)
                .build();

        customerId = customerService.save(customerSaveDto);

        String categoryName = "의류";

        Category category = Category.builder()
                .name(categoryName)
                .build();

        categoryId = categoryService.save(category);

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productName("나이키")
                .price(10000)
                .quantity(6)
                .category(category)
                .build();

        productId = productService.save(productSaveDto);
    }

    @Test
    void findById() {
    }

    @Test
    void findByCustomerId() {
        //주문
        OrderProductSaveDto orderProductSaveDto = OrderProductSaveDto.builder()
                .customerId(customerId)
                .productId(productId)
                .quantity(2)
                .build();

        //개당 10,000원 짜리 2개 씩 2번
        orderProductService.save(orderProductSaveDto);
        orderProductService.save(orderProductSaveDto);

        List<OrdersResponseDto> all = orderService.findByCustomerId(customerId);

        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getQuantity()).isEqualTo(4);
        assertThat(all.get(0).getTotalPrice()).isEqualTo(40000);
    }

    @Test
    void findByCustomerIdAndOrderStatus() {
        //주문
        OrderProductSaveDto orderProductSaveDto = OrderProductSaveDto.builder()
                .customerId(customerId)
                .productId(productId)
                .quantity(2)
                .build();

        //개당 10,000원 짜리 2개 씩 2번
        orderProductService.save(orderProductSaveDto);

        List<OrdersResponseDto> all = orderService.findByCustomerIdAndOrderStatus(customerId, OrderStatus.BASKET);

        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getQuantity()).isEqualTo(2);
        assertThat(all.get(0).getTotalPrice()).isEqualTo(20000);
    }

    @Test
    void updateOrdered() {
        //주문
        OrderProductSaveDto orderProductSaveDto = OrderProductSaveDto.builder()
                .customerId(customerId)
                .productId(productId)
                .quantity(2)
                .build();

        //개당 10,000원 짜리 2개 씩 2번
        Long ordersId = orderProductService.save(orderProductSaveDto);

        orderService.updateOrdered(ordersId, PayMethod.KAKAOPAY);

        List<OrdersResponseDto> all = orderService.findByCustomerIdAndOrderStatus(customerId, OrderStatus.ORDERED);

        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getQuantity()).isEqualTo(2);
        assertThat(all.get(0).getTotalPrice()).isEqualTo(20000);
        assertThat(all.get(0).getOrderStatus()).isEqualTo(OrderStatus.ORDERED);
        assertThat(all.get(0).getPayment().getAmount()).isEqualTo(20000);
        assertThat(all.get(0).getPayment().getPayMethod()).isEqualTo(PayMethod.KAKAOPAY);

        List<OrdersResponseDto> baskets = orderService.findByCustomerIdAndOrderStatus(customerId, OrderStatus.BASKET);

        assertThat(baskets.size()).isEqualTo(0);
    }

    @Test
    void updateCanceled() {
        //주문
        OrderProductSaveDto orderProductSaveDto = OrderProductSaveDto.builder()
                .customerId(customerId)
                .productId(productId)
                .quantity(2)
                .build();

        //개당 10,000원 짜리 2개 씩 2번
        Long ordersId = orderProductService.save(orderProductSaveDto);

        orderService.updateOrdered(ordersId, PayMethod.KAKAOPAY);

        orderService.updateCanceled(ordersId);

        List<OrdersResponseDto> cancels = orderService.findByCustomerIdAndOrderStatus(customerId, OrderStatus.CANCELED);

        assertThat(cancels.size()).isEqualTo(1);
    }

    @Test
    void updateShipment() {
        //주문
        OrderProductSaveDto orderProductSaveDto = OrderProductSaveDto.builder()
                .customerId(customerId)
                .productId(productId)
                .quantity(2)
                .build();

        //개당 10,000원 짜리 2개 씩 2번
        Long ordersId = orderProductService.save(orderProductSaveDto);

        orderService.updateOrdered(ordersId, PayMethod.KAKAOPAY);

        orderService.updateShipment(ordersId, ShipmentStatus.SHIPPING);

        List<OrdersResponseDto> all = orderService.findByCustomerIdAndOrderStatus(customerId, OrderStatus.DELIVERED);

        assertThat(all.get(0).getShipment().getShipmentStatus()).isEqualTo(ShipmentStatus.SHIPPING);
    }

    @Test
    void 주문없음_예외처리_테스트() {
        assertThatThrownBy(() -> orderService.findById(1L)).isInstanceOf(OrderNotFoundEx.class);
    }

    @Test
    void 주문상태_예외처리_테스트() {
        OrderProductSaveDto orderProductSaveDto = OrderProductSaveDto.builder()
                .customerId(customerId)
                .productId(productId)
                .quantity(2)
                .build();

        //개당 10,000원 짜리 2개 씩 2번
        Long ordersId = orderProductService.save(orderProductSaveDto);

        orderService.updateOrdered(ordersId, PayMethod.KAKAOPAY);

        orderService.updateShipment(ordersId, ShipmentStatus.SHIPPING);

        assertThatThrownBy(() -> orderService.updateCanceled(ordersId)).isInstanceOf(OrderStatusEx.class);
    }
}