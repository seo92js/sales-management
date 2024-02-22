package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.product.dto.ProductResponseDto;
import com.seojs.salesmanagement.domain.product.dto.ProductSaveDto;
import com.seojs.salesmanagement.domain.product.dto.ProductUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    Long categoryId;

    @BeforeEach
    void setUp() {
        Category category = Category.builder()
                .name("의류")
                .build();

        categoryId = categoryService.save(category);
    }

    @Test
    void save() {
        Category category = categoryService.findById(categoryId);

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productName("나이키")
                .price(10000)
                .category(category)
                .build();

        Long savedId = productService.save(productSaveDto);

        List<ProductResponseDto> all = productService.findAll();

        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        Category category = categoryService.findById(categoryId);

        String productName = "나이키 모자";
        String description = "야구";
        Integer price = 10000;

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productName(productName)
                .description(description)
                .price(price)
                .category(category)
                .build();

        Long savedId = productService.save(productSaveDto);

        ProductResponseDto findProduct = productService.findById(savedId);

        assertThat(findProduct.getProductName()).isEqualTo(productName);
        assertThat(findProduct.getDescription()).isEqualTo(description);
        assertThat(findProduct.getPrice()).isEqualTo(price);
    }

    @Test
    void findByCategory() {
        Category category = categoryService.findById(categoryId);

        String productName1 = "나이키";
        String description1 = "야구";
        Integer price1 = 10000;

        ProductSaveDto productSaveDto1 = ProductSaveDto.builder()
                .productName(productName1)
                .description(description1)
                .price(price1)
                .category(category)
                .build();

        Long savedId1 = productService.save(productSaveDto1);

        String productName2 = "퓨마";
        String description2 = "축구";
        Integer price2 = 20000;

        ProductSaveDto productSaveDto2 = ProductSaveDto.builder()
                .productName(productName2)
                .description(description2)
                .price(price2)
                .category(category)
                .build();

        Long savedId2 = productService.save(productSaveDto2);

        List<ProductResponseDto> findProducts = productService.findByCategory(category);

        assertThat(findProducts.size()).isEqualTo(2);
        assertThat(findProducts.get(0).getProductName()).isEqualTo(productName1);
        assertThat(findProducts.get(1).getProductName()).isEqualTo(productName2);
        assertThat(findProducts.get(0).getPrice()).isEqualTo(price1);
        assertThat(findProducts.get(1).getPrice()).isEqualTo(price2);
        assertThat(findProducts.get(0).getDescription()).isEqualTo(description1);
        assertThat(findProducts.get(1).getDescription()).isEqualTo(description2);
    }

    @Test
    void findAll() {
        Category category = categoryService.findById(categoryId);

        String productName1 = "나이키";
        String description1 = "야구";
        Integer price1 = 10000;

        ProductSaveDto productSaveDto1 = ProductSaveDto.builder()
                .productName(productName1)
                .description(description1)
                .price(price1)
                .category(category)
                .build();

        Long savedId1 = productService.save(productSaveDto1);

        String productName2 = "퓨마";
        String description2 = "축구";
        Integer price2 = 20000;

        ProductSaveDto productSaveDto2 = ProductSaveDto.builder()
                .productName(productName2)
                .description(description2)
                .price(price2)
                .category(category)
                .build();

        Long savedId2 = productService.save(productSaveDto2);

        List<ProductResponseDto> all = productService.findAll();

        assertThat(all.size()).isEqualTo(2);
        assertThat(all.get(0).getProductName()).isEqualTo(productName1);
        assertThat(all.get(1).getProductName()).isEqualTo(productName2);
        assertThat(all.get(0).getPrice()).isEqualTo(price1);
        assertThat(all.get(1).getPrice()).isEqualTo(price2);
        assertThat(all.get(0).getDescription()).isEqualTo(description1);
        assertThat(all.get(1).getDescription()).isEqualTo(description2);
    }

    @Test
    void delete() {
        Category category = categoryService.findById(categoryId);

        String productName1 = "나이키";
        Integer price1 = 10000;

        ProductSaveDto productSaveDto1 = ProductSaveDto.builder()
                .productName(productName1)
                .price(price1)
                .category(category)
                .build();

        Long savedId1 = productService.save(productSaveDto1);

        List<ProductResponseDto> all = productService.findAll();
        assertThat(all.size()).isEqualTo(1);

        productService.delete(savedId1);

        all = productService.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void update() {
        Category category = categoryService.findById(categoryId);

        String productName = "나이키";
        Integer price = 10000;

        ProductSaveDto productSaveDto1 = ProductSaveDto.builder()
                .productName(productName)
                .price(price)
                .quantity(2)
                .category(category)
                .build();

        Long savedId = productService.save(productSaveDto1);

        List<ProductResponseDto> all = productService.findAll();
        assertThat(all.get(0).getQuantity()).isEqualTo(2);
        assertThat(all.get(0).getPrice()).isEqualTo(10000);

        ProductUpdateDto productUpdateDto = ProductUpdateDto.builder()
                .quantity(10)
                .price(20000)
                .build();

        productService.update(savedId, productUpdateDto);

        all = productService.findAll();
        assertThat(all.get(0).getQuantity()).isEqualTo(10);
        assertThat(all.get(0).getPrice()).isEqualTo(20000);
    }
}