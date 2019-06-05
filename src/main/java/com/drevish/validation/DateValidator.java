package com.drevish.validation;

import com.drevish.validation.annotation.Date;
import com.drevish.validation.error.ValidationError;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateValidator implements AnnotationValidator {
  @Override
  public Optional<ValidationError> validate(Object value, Annotation annotation, String fieldName) {
    Date anno = (Date) annotation;
    if (value instanceof LocalDate) {
      LocalDate date = (LocalDate) value;
      LocalDate min;
      if (anno.minDateIsToday()) {
        min = LocalDate.now();
      } else {
        min = LocalDate.parse(anno.minDate(), DateTimeFormatter.ISO_LOCAL_DATE);
      }
      LocalDate max = LocalDate.parse(anno.maxDate(), DateTimeFormatter.ISO_LOCAL_DATE);

      if (date.isBefore(min) || date.isAfter(max)) {
        return Optional.of(new ValidationError(anno.message(), fieldName));
      }
    }
    return Optional.empty();
  }
}
