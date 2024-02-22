package com.seojs.salesmanagement.domain.product.dto;

import com.seojs.salesmanagement.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveDto {
    private String productName;
    private String description;
    private Integer price;
    private Integer quantity;
    private Category category;

    @Builder
    public ProductSaveDto(String productName, String description, Integer price, Integer quantity, Category category) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
}
