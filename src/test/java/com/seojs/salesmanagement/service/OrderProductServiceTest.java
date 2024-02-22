package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.customer.Role;
import com.seojs.salesmanagement.domain.customer.dto.CustomerResponseDto;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import com.seojs.salesmanagement.domain.orderproduct.dto.OrderProductSaveDto;
import com.seojs.salesmanagement.domain.product.dto.ProductSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderProductServiceTest {
    @Autowired
    OrderProductService orderProductService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

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
                .category(category)
                .quantity(4)
                .build();

        productId = productService.save(productSaveDto);
    }

    @Test
    void save() {
        //주문
        OrderProductSaveDto orderProductSaveDto = OrderProductSaveDto.builder()
                .customerId(customerId)
                .productId(productId)
                .quantity(2)
                .build();

        //개당 10,000원 짜리 2개 씩 2번
        orderProductService.save(orderProductSaveDto);
        orderProductService.save(orderProductSaveDto);

        CustomerResponseDto byId = customerService.findById(customerId);

        assertThat(byId.getOrders().get(0).getOrderProducts().size()).isEqualTo(2);
        assertThat(byId.getOrders().get(0).getQuantity()).isEqualTo(4);
        assertThat(byId.getOrders().get(0).getTotalPrice()).isEqualTo(40000);
    }
}