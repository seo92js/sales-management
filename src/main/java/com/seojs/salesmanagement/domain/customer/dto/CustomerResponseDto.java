package com.seojs.salesmanagement.domain.customer.dto;

import com.seojs.salesmanagement.domain.customer.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerResponseDto {
    private String loginId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public CustomerResponseDto(Customer customer) {
        this.loginId = customer.getLoginId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        this.address = customer.getAddress();
    }
}
