package com.seojs.salesmanagement.domain.product;

import com.seojs.salesmanagement.domain.category.Category;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByCategory(Category category);
}
