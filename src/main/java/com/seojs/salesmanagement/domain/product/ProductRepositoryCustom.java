package com.seojs.salesmanagement.domain.product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByCategoryName(String categoryName);
}
