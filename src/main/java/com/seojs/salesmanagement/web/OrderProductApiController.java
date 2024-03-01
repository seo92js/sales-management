package com.seojs.salesmanagement.web;

import com.seojs.salesmanagement.domain.orderproduct.dto.OrderProductSaveDto;
import com.seojs.salesmanagement.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderProductApiController {
    private final OrderProductService orderProductService;

    @PostMapping("/api/v1/orderproduct")
    public Long save(@RequestBody OrderProductSaveDto orderProductSaveDto) {
        return orderProductService.save(orderProductSaveDto);
    }
}
