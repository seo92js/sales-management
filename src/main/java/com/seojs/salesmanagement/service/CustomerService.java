package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.customer.CustomerRepository;
import com.seojs.salesmanagement.domain.customer.dto.CustomerResponseDto;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Long save(CustomerSaveDto customerSaveDto) {
        Customer customer = Customer.builder()
                .loginId(customerSaveDto.getLoginId())
                .password(customerSaveDto.getPassword())
                .name(customerSaveDto.getName())
                .email(customerSaveDto.getEmail())
                .phoneNumber(customerSaveDto.getPhoneNumber())
                .address(customerSaveDto.getAddress())
                .role(customerSaveDto.getRole())
                .build();

        return customerRepository.save(customer).getId();
    }

    @Transactional
    public CustomerResponseDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        return new CustomerResponseDto(customer);
    }

    @Transactional
    public List<CustomerResponseDto> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerResponseDto::new)
                .collect(Collectors.toList());
    }
}
