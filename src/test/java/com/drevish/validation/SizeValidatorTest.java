package com.drevish.validation;

import com.drevish.validation.error.ValidationError;
import org.junit.Test;

import static com.drevish.validation.ValidationTestUtils.shouldNotReturnError;
import static com.drevish.validation.ValidationTestUtils.shouldReturnError;

public class SizeValidatorTest {
  @Test
  public void shouldReturnTooShortError() {
    TestEntity entity = new TestEntity("q", null, null);
    String fieldName = "string";
    ValidationError expected = new ValidationError("Size", fieldName);
    shouldReturnError(entity, fieldName, expected);
  }

  @Test
  public void shouldReturnTooLongError() {
    TestEntity entity = new TestEntity("qqqqqqqqqqqqqq", null, null);
    String fieldName = "string";
    ValidationError expected = new ValidationError("Size", fieldName);
    shouldReturnError(entity, fieldName, expected);
  }

  @Test
  public void shouldNotReturnSizeError() {
    TestEntity entity = new TestEntity("qqq", null, null);
    String fieldName = "string";
    ValidationError expected = new ValidationError("Size", fieldName);
    shouldNotReturnError(entity, fieldName, expected);
  }
}