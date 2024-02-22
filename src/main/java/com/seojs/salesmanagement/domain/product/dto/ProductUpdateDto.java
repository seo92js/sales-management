package com.seojs.salesmanagement.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductUpdateDto {
    Integer quantity;
    Integer price;

    @Builder
    public ProductUpdateDto(Integer quantity, Integer price) {
        this.quantity = quantity;
        this.price = price;
    }
}
