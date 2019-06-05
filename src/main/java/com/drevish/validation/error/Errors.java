package com.drevish.validation.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Errors {
  private Map<String, List<ValidationError>> errors = new HashMap<>();
  private boolean hasErrors;

  public boolean hasErrors() {
    return hasErrors;
  }

  public void addError(String field, ValidationError error) {
    errors.computeIfAbsent(field, k -> new ArrayList<>());
    errors.get(field).add(error);
  }

  public void addErrors(String field, List<ValidationError> validationErrors) {
    if (!validationErrors.isEmpty()) {
      hasErrors = true;
    }
    errors.computeIfAbsent(field, k -> new ArrayList<>());
    errors.get(field).addAll(validationErrors);
  }

  public List<ValidationError> getErrors(String field) {
    return errors.get(field);
  }
}
