package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.payment.Payment;
import com.seojs.salesmanagement.domain.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    public Long save(Payment payment) {
        return paymentRepository.save(payment).getId();
    }
}
