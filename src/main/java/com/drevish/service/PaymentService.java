package com.drevish.service;

import com.drevish.model.entity.Payment;
import com.drevish.validation.error.Errors;

public interface PaymentService {
  Errors validate(Payment payment);
}
