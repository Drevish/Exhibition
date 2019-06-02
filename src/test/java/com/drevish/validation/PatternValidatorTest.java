package com.drevish.validation;

import com.drevish.validation.error.ValidationError;
import org.junit.Test;

import static com.drevish.validation.ValidationTestUtils.shouldNotReturnError;
import static com.drevish.validation.ValidationTestUtils.shouldReturnError;

public class PatternValidatorTest {
  @Test
  public void shouldReturnPatternError() {
    TestEntity entity = new TestEntity("q", null, null);
    String fieldName = "string";
    ValidationError expected = new ValidationError("Pattern", fieldName);
    shouldReturnError(entity, fieldName, expected);
  }

  @Test
  public void shouldNotReturnPatternError() {
    TestEntity entity = new TestEntity("123", null, null);
    String fieldName = "string";
    ValidationError expected = new ValidationError("Pattern", fieldName);
    shouldNotReturnError(entity, fieldName, expected);
  }
}