package com.seojs.salesmanagement.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.salesmanagement.config.auth.LoginDto;
import com.seojs.salesmanagement.config.jwt.JwtProperties;
import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.category.CategoryRepository;
import com.seojs.salesmanagement.domain.customer.Role;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import com.seojs.salesmanagement.domain.orderproduct.dto.OrderProductSaveDto;
import com.seojs.salesmanagement.domain.orders.OrderStatus;
import com.seojs.salesmanagement.domain.orders.Orders;
import com.seojs.salesmanagement.domain.orders.OrdersRepository;
import com.seojs.salesmanagement.domain.payment.PayMethod;
import com.seojs.salesmanagement.domain.product.Product;
import com.seojs.salesmanagement.domain.product.ProductRepository;
import com.seojs.salesmanagement.domain.shipment.ShipmentStatus;
import com.seojs.salesmanagement.service.CustomerService;
import com.seojs.salesmanagement.service.OrderProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OrderApiControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CustomerService customerService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderProductService orderProductService;

    @Autowired
    OrdersRepository ordersRepository;

    Long customerId;
    Long categoryId;
    Long productId;
    Long orderProductId;
    String accessToken;

    @BeforeEach
    void setUp() throws Exception {
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
                .address(address)
                .name(name)
                .email(email)
                .role(role)
                .phoneNumber(phoneNumber)
                .build();

        customerId = customerService.save(customerSaveDto);

        String postUrl = "/login";

        LoginDto loginDto = new LoginDto("loginId", "password");

        MvcResult result = mvc.perform(post(postUrl)
                        .content(objectMapper.writeValueAsString(loginDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        accessToken = JwtProperties.TOKEN_PREFIX + result.getResponse().getContentAsString();

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

        OrderProductSaveDto orderProductSaveDto = OrderProductSaveDto.builder()
                .customerId(customerId)
                .productId(productId)
                .quantity(1)
                .build();

        orderProductId = orderProductService.save(orderProductSaveDto);
    }

    @Test
    void findById() throws Exception {
    }

    @Test
    void findByCustomerId() throws Exception {
        String getUrl = "/api/v1/orders/customer/" + customerId;

        mvc.perform(get(getUrl)
                        .header("Authorization", accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].quantity", Matchers.is(1)));
    }

    @Test
    void findByCustomerIdAndOrderStatus() throws Exception {
        String getUrl = "/api/v1/orders/customer/" + customerId + "/status/" + OrderStatus.BASKET;

        mvc.perform(get(getUrl)
                        .header("Authorization", accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].quantity", Matchers.is(1)));
    }

    @Test
    void updateCanceled() throws Exception {
        String patchUrl = "/api/v1/orders/cancel/" + orderProductId;

        mvc.perform(patch(patchUrl)
                        .header("Authorization", accessToken))
                .andExpect(status().isOk());

        String getUrl = "/api/v1/orders/customer/" + customerId + "/status/" + OrderStatus.CANCELED;

        mvc.perform(get(getUrl)
                        .header("Authorization", accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    void updateOrdered() throws Exception {
        String patchUrl = "/api/v1/orders/" + orderProductId + "/payment/" + PayMethod.KAKAOPAY;

        mvc.perform(patch(patchUrl)
                        .header("Authorization", accessToken))
                .andExpect(status().isOk());

        List<Orders> all = ordersRepository.findAll();

        assertThat(all.get(0).getPayment().getPayMethod()).isEqualTo(PayMethod.KAKAOPAY);
    }

    @Test
    void testUpdateOrdered() throws Exception {
        String patchUrl = "/api/v1/orders/" + orderProductId + "/shipment/" + ShipmentStatus.SHIPPING;

        mvc.perform(patch(patchUrl)
                        .header("Authorization", accessToken))
                .andExpect(status().isOk());

        List<Orders> all = ordersRepository.findAll();

        assertThat(all.get(0).getShipment().getShipmentStatus()).isEqualTo(ShipmentStatus.SHIPPING);
    }
}