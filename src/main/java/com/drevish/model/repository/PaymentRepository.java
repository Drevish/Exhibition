package com.drevish.model.repository;

import com.drevish.model.entity.Payment;

import java.util.Optional;

public interface PaymentRepository {
  Payment addPayment(Payment payment);

  Optional<Payment> findById(Long id);
}
