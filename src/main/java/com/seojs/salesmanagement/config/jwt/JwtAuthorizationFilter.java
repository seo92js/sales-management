package com.seojs.salesmanagement.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.seojs.salesmanagement.config.auth.PrincipalDetails;
import com.seojs.salesmanagement.domain.customer.Customer;
import com.seojs.salesmanagement.domain.customer.CustomerRepository;
import com.seojs.salesmanagement.exception.CustomerNotFoundEx;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private CustomerRepository customerRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, CustomerRepository customerRepository) {
        super(authenticationManager);
        this.customerRepository = customerRepository;
    }

    // 인증이 필요할 때
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);

        // 토큰 X
        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "");

        String loginId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                .verify(jwtToken)
                .getClaim("loginId")
                .asString();

        if (loginId != null) {
            Customer customer = customerRepository.findByLoginId(loginId).orElseThrow(() -> new CustomerNotFoundEx("고객이 없습니다. loginId = " + loginId));

            PrincipalDetails principalDetails = new PrincipalDetails(customer);

            //서명 완료시
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}
