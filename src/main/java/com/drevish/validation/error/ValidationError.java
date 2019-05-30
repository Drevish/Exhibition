package com.drevish.validation.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {
  private String message;
  private String fieldName;
}
