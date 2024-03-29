package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.category.Category;
import com.seojs.salesmanagement.domain.category.CategoryRepository;
import com.seojs.salesmanagement.exception.CategoryNotFoundEx;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(Category category) {
        return categoryRepository.save(category).getId();
    }

    @Transactional
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundEx("카테고리가 없습니다. id = " + id));
    }

    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
