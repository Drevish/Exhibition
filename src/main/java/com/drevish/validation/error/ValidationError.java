package com.drevish.validation.error;

import lombok.AllArgsConstructor;
import lombok.Data;

//TODO: page for every showroom
//TODO: internationalization for errors and headers etc
@Data
@AllArgsConstructor
public class ValidationError {
  private String message;
  private String fieldName;
}
