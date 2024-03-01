package com.seojs.salesmanagement.web;

import com.seojs.salesmanagement.domain.customer.dto.CustomerResponseDto;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import com.seojs.salesmanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomerApiController {
    private final CustomerService customerService;

    @PostMapping("/api/v1/customer")
    public Long save(@RequestBody CustomerSaveDto customerSaveDto) {
        return customerService.save(customerSaveDto);
    }

    @GetMapping("/api/v1/customer/{id}")
    public CustomerResponseDto findById(@PathVariable(name = "id") Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/api/v1/customers")
    public List<CustomerResponseDto> findAll() {
        return customerService.findAll();
    }

}
