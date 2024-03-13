package com.seojs.salesmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.salesmanagement.config.auth.LoginDto;
import com.seojs.salesmanagement.domain.customer.Role;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import com.seojs.salesmanagement.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LoginTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

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
                .address(address)
                .name(name)
                .email(email)
                .role(role)
                .phoneNumber(phoneNumber)
                .build();

        customerService.save(customerSaveDto);
    }

    @Test
    void login() throws Exception {
        String postUrl = "/login";

        LoginDto loginDto = new LoginDto("loginId", "password");

        mvc.perform(post(postUrl)
                .content(objectMapper.writeValueAsString(loginDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().exists("Authorization"));
    }

    @Test
    void login_실패() throws Exception {
        String postUrl = "/login";

        LoginDto loginDto = new LoginDto("loginId", "passwor");

        mvc.perform(post(postUrl)
                        .content(objectMapper.writeValueAsString(loginDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(header().doesNotExist("Authorization"));
    }
}
