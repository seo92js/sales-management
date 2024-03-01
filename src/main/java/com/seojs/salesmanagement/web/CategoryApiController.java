package com.seojs.salesmanagement.web;

import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping("/api/v1/category")
    public Long save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/api/v1/category/{id}")
    public Category category(@PathVariable(name = "id") Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/api/v1/categories")
    public List<Category> findAll() {
        return categoryService.findAll();
    }
}
