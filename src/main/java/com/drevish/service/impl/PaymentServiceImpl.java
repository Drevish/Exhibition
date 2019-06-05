package com.drevish.service.impl;

import com.drevish.model.entity.Payment;
import com.drevish.model.repository.PaymentRepository;
import com.drevish.service.PaymentService;
import com.drevish.validation.Validator;
import com.drevish.validation.error.Errors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
  private final PaymentRepository repository;

  @Override
  public Errors validate(Payment payment) {
    return Validator.validate(payment);
  }
}
