package com.seojs.salesmanagement.domain.product.dto;

import com.seojs.salesmanagement.domain.product.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private String productName;
    private String description;
    private Integer price;
    private Integer quantity;

    public ProductResponseDto(Product product) {
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}
