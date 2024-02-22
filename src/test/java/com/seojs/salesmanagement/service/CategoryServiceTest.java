package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.category.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @Test
    void save() {
        String categoryName = "의류";

        Category category = Category.builder()
                .name(categoryName)
                .build();

        Long savedId = categoryService.save(category);

        List<Category> all = categoryService.findAll();

        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        String categoryName = "의류";

        Category category = Category.builder()
                .name(categoryName)
                .build();

        Long savedId = categoryService.save(category);

        Category findCategory = categoryService.findById(savedId);

        assertThat(findCategory.getName()).isEqualTo(categoryName);
    }

    @Test
    void findAll() {
        String categoryName1 = "의류";
        String categoryName2 = "잡화";

        Category category1= Category.builder()
                .name(categoryName1)
                .build();

        Category category2 = Category.builder()
                .name(categoryName2)
                .build();

        Long savedId1 = categoryService.save(category1);
        Long savedId2 = categoryService.save(category2);

        List<Category> all = categoryService.findAll();

        assertThat(all.size()).isEqualTo(2);
        assertThat(all.get(0).getName()).isEqualTo("의류");
        assertThat(all.get(1).getName()).isEqualTo("잡화");
    }
}