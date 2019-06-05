package com.drevish.validation;

import com.drevish.validation.error.Errors;
import com.drevish.validation.error.ValidationError;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidationTestUtils {
  static public void shouldReturnError(TestEntity entity, String fieldName,
                                       ValidationError expected) {
    Errors errors = Validator.validate(entity);
    List<ValidationError> stringErrors = errors.getErrors(fieldName);
    assertNotNull(stringErrors);
    assertTrue(stringErrors.contains(expected));
  }

  static public void shouldNotReturnError(TestEntity entity, String fieldName,
                                       ValidationError expected) {
    Errors errors = Validator.validate(entity);
    List<ValidationError> stringErrors = errors.getErrors(fieldName);
    assertNotNull(stringErrors);
    assertFalse(stringErrors.contains(expected));
  }
}
