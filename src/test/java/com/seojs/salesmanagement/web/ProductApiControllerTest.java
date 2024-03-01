package com.seojs.salesmanagement.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.category.CategoryRepository;
import com.seojs.salesmanagement.domain.product.dto.ProductSaveDto;
import com.seojs.salesmanagement.domain.product.dto.ProductUpdateDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductApiControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;

    @Autowired
    CategoryRepository categoryRepository;

    Long categoryId;

    @BeforeEach
    void setUp() {
        String categoryName = "의류";

        Category category = Category.builder()
                .name(categoryName)
                .build();

        categoryId = categoryRepository.save(category).getId();
    }

    @Test
    void save() throws Exception {
        String postUrl = "/api/v1/product";

        String productName = "모자";
        Integer price = 10000;
        Integer quantity = 4;
        Category category = categoryRepository.findById(categoryId).get();
        String description = "설명";

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .category(category)
                .description(description)
                .build();

        mvc.perform(post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productSaveDto)))
                .andExpect(status().isOk());

        String getUrl = "/api/v1/products";

        mvc.perform(get(getUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void findById() throws Exception {
        String postUrl = "/api/v1/product";

        String productName = "모자";
        Integer price = 10000;
        Integer quantity = 4;
        Category category = categoryRepository.findById(categoryId).get();
        String description = "설명";

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .category(category)
                .description(description)
                .build();

        MvcResult result = mvc.perform(post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productSaveDto)))
                .andExpect(status().isOk()).andReturn();

        Long id = extractedId(result);

        String getUrl = "/api/v1/product/" + id;

        mvc.perform(get(getUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", Matchers.is(productName)))
                .andExpect(jsonPath("$.quantity", Matchers.is(quantity)))
                .andExpect(jsonPath("$.description", Matchers.is(description)));
    }

    @Test
    void findByCategoryName() throws Exception {
        String postUrl = "/api/v1/product";

        String productName = "모자";
        Integer price = 10000;
        Integer quantity = 4;
        Category category = categoryRepository.findById(categoryId).get();
        String description = "설명";

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .category(category)
                .description(description)
                .build();

        mvc.perform(post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productSaveDto)))
                .andExpect(status().isOk()).andReturn();

        String getUrl = "/api/v1/product/category/" + category.getName();

        mvc.perform(get(getUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName", Matchers.is(productName)))
                .andExpect(jsonPath("$[0].quantity", Matchers.is(quantity)))
                .andExpect(jsonPath("$[0].description", Matchers.is(description)));
    }

    @Test
    void findAll() throws Exception {
        String postUrl = "/api/v1/product";

        String productName = "모자";
        Integer price = 10000;
        Integer quantity = 4;
        Category category = categoryRepository.findById(categoryId).get();
        String description = "설명";

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .category(category)
                .description(description)
                .build();

        mvc.perform(post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productSaveDto)))
                .andExpect(status().isOk()).andReturn();

        String getUrl = "/api/v1/products";

        mvc.perform(get(getUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName", Matchers.is(productName)))
                .andExpect(jsonPath("$[0].quantity", Matchers.is(quantity)))
                .andExpect(jsonPath("$[0].description", Matchers.is(description)));
    }

    @Test
    void delete() throws Exception {
        String postUrl = "/api/v1/product";

        String productName = "모자";
        Integer price = 10000;
        Integer quantity = 4;
        Category category = categoryRepository.findById(categoryId).get();
        String description = "설명";

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .category(category)
                .description(description)
                .build();

        MvcResult result = mvc.perform(post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productSaveDto)))
                .andExpect(status().isOk()).andReturn();

        Long id = extractedId(result);

        String deleteUrl = "/api/v1/product/" + id;

        mvc.perform(MockMvcRequestBuilders.delete(deleteUrl))
                .andExpect(status().isOk());

        String getUrl = "/api/v1/products";

        mvc.perform(get(getUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void update() throws Exception {
        String postUrl = "/api/v1/product";

        String productName = "모자";
        Integer price = 10000;
        Integer quantity = 4;
        Category category = categoryRepository.findById(categoryId).get();
        String description = "설명";

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .category(category)
                .description(description)
                .build();

        MvcResult result = mvc.perform(post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productSaveDto)))
                .andExpect(status().isOk()).andReturn();

        Long id = extractedId(result);

        String patchUrl = "/api/v1/product/" + id;

        ProductUpdateDto productUpdateDto = ProductUpdateDto.builder()
                .price(20000)
                .quantity(10)
                .build();

        mvc.perform(patch(patchUrl)
                .content(objectMapper.writeValueAsBytes(productUpdateDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String getUrl = "/api/v1/products";

        mvc.perform(get(getUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price", Matchers.is(20000)))
                .andExpect(jsonPath("$[0].quantity", Matchers.is(10)))
                .andExpect(jsonPath("$[0].description", Matchers.is(description)));
    }

    private Long extractedId(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        MockHttpServletResponse response = result.getResponse();
        String responseBody = response.getContentAsString();
        return objectMapper.readValue(responseBody, Long.class);
    }
}