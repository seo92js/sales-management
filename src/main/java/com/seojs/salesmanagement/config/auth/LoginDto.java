package com.seojs.salesmanagement.config.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {
    private String username; // Customer 기준 loginId
    private String password;

    @Builder
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
