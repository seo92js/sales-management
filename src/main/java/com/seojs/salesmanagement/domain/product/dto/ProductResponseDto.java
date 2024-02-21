package com.seojs.salesmanagement.domain.product.dto;

import com.seojs.salesmanagement.domain.product.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private String productName;
    private Integer price;

    public ProductResponseDto(Product product) {
        this.productName = product.getProductName();
        this.price = product.getPrice();
    }
}
