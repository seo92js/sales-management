package com.seojs.salesmanagement.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.category.CategoryRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CategoryApiControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void save() throws Exception {
        String postUrl = "/api/v1/category";

        Category category = Category.builder()
                .name("도서")
                .build();

        mvc.perform(post(postUrl)
                        .content(objectMapper.writeValueAsString(category))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String getUrl = "/api/v1/categories";

        mvc.perform(get(getUrl))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void findById() throws Exception {
        String postUrl = "/api/v1/category";

        String name = "도서";

        Category category = Category.builder()
                .name(name)
                .build();

        MvcResult result = mvc.perform(post(postUrl)
                        .content(objectMapper.writeValueAsString(category))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        Long id = extractedId(result);

        String getUrl = "/api/v1/category/" + id;

        mvc.perform(get(getUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(name)));
    }

    @Test
    void findAll() throws Exception {
        String postUrl = "/api/v1/category";

        String name = "도서";

        Category category = Category.builder()
                .name(name)
                .build();

        mvc.perform(post(postUrl)
                        .content(objectMapper.writeValueAsString(category))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String getUrl = "/api/v1/categories";

        mvc.perform(get(getUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(name)));
    }

    private Long extractedId(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        MockHttpServletResponse response = result.getResponse();
        String responseBody = response.getContentAsString();
        return objectMapper.readValue(responseBody, Long.class);
    }
}