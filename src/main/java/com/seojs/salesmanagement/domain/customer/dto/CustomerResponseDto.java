package com.seojs.salesmanagement.domain.customer.dto;

import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.orders.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CustomerResponseDto {
    private String loginId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private List<Orders> orders = new ArrayList<>();

    public CustomerResponseDto(Customer customer) {
        this.loginId = customer.getLoginId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        this.address = customer.getAddress();
        this.orders = customer.getOrders();
    }
}
