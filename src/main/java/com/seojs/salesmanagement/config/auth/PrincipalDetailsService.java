package com.seojs.salesmanagement.config.auth;

import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.customer.CustomerRepository;
import com.seojs.salesmanagement.exception.CustomerNotFoundEx;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByLoginId(username).orElseThrow(() -> new CustomerNotFoundEx("고객이 없습니다. loginId = " + username));

        return new PrincipalDetails(customer);
    }
}
