package com.seojs.salesmanagement.domain.customer;

import com.seojs.salesmanagement.domain.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Role role;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    //연관관계
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Builder
    public Customer(String loginId, String password, String name, String email, String phoneNumber, String address, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }
}
