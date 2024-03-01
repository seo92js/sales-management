package com.seojs.salesmanagement.domain.orderproduct.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderProductSaveDto {
    private Long customerId;
    private Long productId;
    private Integer quantity;

    @Builder
    public OrderProductSaveDto(Long customerId, Long productId, Integer quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
