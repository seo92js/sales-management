package com.seojs.salesmanagement;

import com.seojs.salesmanagement.domain.customer.Role;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import com.seojs.salesmanagement.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LoginTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    CustomerService customerService;

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

        mvc.perform(formLogin(postUrl)
                        .user("loginId")
                        .password("password"))
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated());
    }

    @Test
    void login_실패() throws Exception {
        String postUrl = "/login";

        mvc.perform(formLogin(postUrl)
                .user("loginId")
                .password("pass"))
                .andExpect(unauthenticated());
    }
}
