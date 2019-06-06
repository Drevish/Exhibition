package com.drevish.model.repository;

import com.drevish.model.entity.Payment;

import java.util.Optional;

public interface PaymentRepository {
  Optional<Payment> addPayment(Payment payment);

  Optional<Payment> findById(Long id);
}
