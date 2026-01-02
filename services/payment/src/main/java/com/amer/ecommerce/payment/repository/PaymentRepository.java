package com.amer.ecommerce.payment.repository;

import com.amer.ecommerce.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
