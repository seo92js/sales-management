package com.seojs.salesmanagement.domain.product.dto;

import com.seojs.salesmanagement.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveDto {
    private String productName;
    private Integer price;
    private Category category;

    public ProductSaveDto(String productName, Integer price, Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }
}
