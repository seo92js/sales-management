package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.customer.CustomerRepository;
import com.seojs.salesmanagement.domain.customer.dto.CustomerResponseDto;
import com.seojs.salesmanagement.domain.customer.dto.CustomerSaveDto;
import com.seojs.salesmanagement.exception.CustomerDupliacateEx;
import com.seojs.salesmanagement.exception.CustomerNotFoundEx;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long save(CustomerSaveDto customerSaveDto) {
        checkDuplicateCustomer(customerSaveDto.getLoginId());

        Customer customer = Customer.builder()
                .loginId(customerSaveDto.getLoginId())
                .password(bCryptPasswordEncoder.encode(customerSaveDto.getPassword()))
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
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundEx("고객이 없습니다. id = " + id));

        return new CustomerResponseDto(customer);
    }

    @Transactional
    public List<CustomerResponseDto> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerResponseDto::new)
                .collect(Collectors.toList());
    }

    private void checkDuplicateCustomer(String loginId) {
        customerRepository.findByLoginId(loginId).ifPresent(
                existCustomer -> {throw new CustomerDupliacateEx("중복된 고객이 있습니다. loginId = " + loginId);
        });
    }
}
