package com.drevish.validation;

import com.drevish.validation.error.ValidationError;
import org.junit.Before;
import org.junit.Test;

import static com.drevish.validation.ValidationTestUtils.shouldNotReturnError;
import static com.drevish.validation.ValidationTestUtils.shouldReturnError;

public class NotNullValidatorTest {
  private TestEntity nullEntity;

  @Before
  public void before() {
    nullEntity = new TestEntity(null, null, null);
  }

  @Test
  public void shouldReturnStringNotNullError() {
    String fieldName = "string";
    ValidationError expected = new ValidationError("NotNull", fieldName);
    shouldReturnError(nullEntity, fieldName, expected);
  }

  @Test
  public void shouldReturnLocalDateNotNullError() {
    String fieldName = "minToday";
    ValidationError expected = new ValidationError("NotNull", fieldName);
    shouldReturnError(nullEntity, fieldName, expected);
  }

  @Test
  public void shouldNotReturnNotNullError() {
    nullEntity.setString("test");
    String fieldName = "string";
    ValidationError expected = new ValidationError("NotNull", fieldName);
    shouldNotReturnError(nullEntity, fieldName, expected);
  }
}