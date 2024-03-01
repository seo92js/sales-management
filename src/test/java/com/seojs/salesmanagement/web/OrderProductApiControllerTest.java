package com.seojs.salesmanagement.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.category.CategoryRepository;
import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.customer.CustomerRepository;
import com.seojs.salesmanagement.domain.customer.Role;
import com.seojs.salesmanagement.domain.orderproduct.dto.OrderProductSaveDto;
import com.seojs.salesmanagement.domain.orders.Orders;
import com.seojs.salesmanagement.domain.orders.OrdersRepository;
import com.seojs.salesmanagement.domain.product.Product;
import com.seojs.salesmanagement.domain.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OrderProductApiControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrdersRepository ordersRepository;

    Long customerId;
    Long categoryId;
    Long productId;

    @BeforeEach
    void setUp() {
        String loginId = "loginId";
        String password = "password";
        String name = "seo";
        String email = "seojs@naver.com";
        String phoneNumber = "010-1111-1111";
        String address = "서울시 강서구 등촌동";
        Role role = Role.USER;

        Customer customer = Customer.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address)
                .role(role)
                .build();

        customerId = customerRepository.save(customer).getId();

        String categoryName = "의류";

        Category category = Category.builder()
                .name(categoryName)
                .build();

        categoryId = categoryRepository.save(category).getId();

        String productName = "모자";
        Integer price = 10000;
        Integer quantity = 4;
        Category findCategory = categoryRepository.findById(categoryId).get();
        String description = "설명";

        Product product = Product.builder()
                .productName(productName)
                .price(price)
                .description(description)
                .quantity(quantity)
                .category(findCategory)
                .build();

        productId = productRepository.save(product).getId();
    }

    @Test
    void save() throws Exception {
        String postUrl = "/api/v1/orderproduct";

        OrderProductSaveDto orderProductSaveDto = OrderProductSaveDto.builder()
                .customerId(customerId)
                .productId(productId)
                .quantity(1)
                .build();

        mvc.perform(post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderProductSaveDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<Orders> all = ordersRepository.findByCustomerId(customerId);

        assertThat(all.size()).isEqualTo(1);
    }
}