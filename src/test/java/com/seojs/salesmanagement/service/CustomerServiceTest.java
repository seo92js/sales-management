package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.customer.Role;
import com.seojs.salesmanagement.domain.customer.dto.CustomerResponseDto;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    void save() {
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

        Long savedId = customerService.save(customerSaveDto);

        List<CustomerResponseDto> all = customerService.findAll();

        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    void findById() {
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

        Long savedId = customerService.save(customerSaveDto);

        CustomerResponseDto findCustomer = customerService.findById(savedId);

        assertThat(findCustomer.getLoginId()).isEqualTo(loginId);
        assertThat(findCustomer.getName()).isEqualTo(name);
        assertThat(findCustomer.getEmail()).isEqualTo(email);
        assertThat(findCustomer.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(findCustomer.getAddress()).isEqualTo(address);
    }

    @Test
    void findAll() {
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

        Long savedId1 = customerService.save(customerSaveDto);

        Long savedId2 = customerService.save(customerSaveDto);

        List<CustomerResponseDto> all = customerService.findAll();

        assertThat(all.size()).isEqualTo(2);
    }
}