package com.drevish.validation;

import com.drevish.validation.error.ValidationError;
import org.junit.Test;

import java.time.LocalDate;

import static com.drevish.validation.ValidationTestUtils.shouldNotReturnError;
import static com.drevish.validation.ValidationTestUtils.shouldReturnError;

public class DateValidatorTest {
  @Test
  public void shouldReturnBeforeTodayError() {
    TestEntity entity = new TestEntity(null, LocalDate.now().minusDays(1), null);
    String fieldName = "minToday";
    ValidationError expected = new ValidationError("DateToday", fieldName);
    shouldReturnError(entity, fieldName, expected);
  }

  @Test
  public void shouldNotReturnBeforeTodayError() {
    TestEntity entity = new TestEntity(null, LocalDate.now().plusDays(1), null);
    String fieldName = "minToday";
    ValidationError expected = new ValidationError("DateToday", fieldName);
    shouldNotReturnError(entity, fieldName, expected);
  }

  @Test
  public void shouldReturnTooSmallDateError() {
    TestEntity entity = new TestEntity(null, null, LocalDate.parse("1990-01-01"));
    String fieldName = "date";
    ValidationError expected = new ValidationError("Date", fieldName);
    shouldReturnError(entity, fieldName, expected);
  }

  @Test
  public void shouldReturnTooBigDateError() {
    TestEntity entity = new TestEntity(null, null, LocalDate.parse("2025-01-01"));
    String fieldName = "date";
    ValidationError expected = new ValidationError("Date", fieldName);
    shouldReturnError(entity, fieldName, expected);
  }

  @Test
  public void shouldNotReturnDateError() {
    TestEntity entity = new TestEntity(null, null, LocalDate.parse("2015-01-01"));
    String fieldName = "date";
    ValidationError expected = new ValidationError("Date", fieldName);
    shouldNotReturnError(entity, fieldName, expected);
  }
}