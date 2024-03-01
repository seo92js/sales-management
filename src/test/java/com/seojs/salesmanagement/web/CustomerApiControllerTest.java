package com.seojs.salesmanagement.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.salesmanagement.domain.customer.Role;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CustomerApiControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void save() throws Exception {
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
                .phoneNumber(phoneNumber)
                .email(email)
                .address(address)
                .role(role)
                .build();

        String postUrl = "/api/v1/customer";

        mvc.perform(MockMvcRequestBuilders.post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerSaveDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String getUrl = "/api/v1/customers";

        mvc.perform(MockMvcRequestBuilders.get(getUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void findById() throws Exception {
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
                .phoneNumber(phoneNumber)
                .email(email)
                .address(address)
                .role(role)
                .build();

        String postUrl = "/api/v1/customer";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(postUrl).content(objectMapper.writeValueAsString(customerSaveDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Long id = extractedId(result);

        String getUrl = "/api/v1/customer/" + id;

        mvc.perform(MockMvcRequestBuilders.get(getUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", Matchers.is(phoneNumber)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.is(address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(email)));
    }

    @Test
    void findAll() throws Exception {
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
                .phoneNumber(phoneNumber)
                .email(email)
                .address(address)
                .role(role)
                .build();

        String postUrl = "/api/v1/customer";

        mvc.perform(MockMvcRequestBuilders.post(postUrl).content(objectMapper.writeValueAsString(customerSaveDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String getUrl = "/api/v1/customers";

        mvc.perform(MockMvcRequestBuilders.get(getUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address", Matchers.is(address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber", Matchers.is(phoneNumber)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is(email)));

    }

    private Long extractedId(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        MockHttpServletResponse response = result.getResponse();
        String responseBody = response.getContentAsString();
        return objectMapper.readValue(responseBody, Long.class);
    }
}