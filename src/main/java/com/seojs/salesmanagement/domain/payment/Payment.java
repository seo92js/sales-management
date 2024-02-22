package com.seojs.salesmanagement.domain.payment;

import com.seojs.salesmanagement.domain.orders.Orders;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    private PayMethod payMethod;

    @OneToOne(mappedBy = "payment")
    private Orders orders;

    @Builder
    public Payment(Integer amount, PayMethod payMethod) {
        this.amount = amount;
        this.payMethod = payMethod;
    }
}
