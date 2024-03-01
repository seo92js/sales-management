package com.seojs.salesmanagement.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateDto {
    Integer quantity;
    Integer price;

    @Builder
    public ProductUpdateDto(Integer quantity, Integer price) {
        this.quantity = quantity;
        this.price = price;
    }
}
