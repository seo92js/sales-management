package com.seojs.salesmanagement.domain.customer.dto;

import com.seojs.salesmanagement.domain.customer.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerSaveDto {
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Role role;

    @Builder
    public CustomerSaveDto(String loginId, String password, String name, String email, String phoneNumber, String address, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }
}
