package com.drevish.validation;

import com.drevish.validation.annotation.NotNull;
import com.drevish.validation.error.ValidationError;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class NotNullValidator implements AnnotationValidator {
  @Override
  public Optional<ValidationError> validate(Object value, Annotation annotation, String fieldName) {
    NotNull anno = (NotNull) annotation;
    if (value == null) {
      return Optional.of(new ValidationError(anno.message(), fieldName));
    }
    return Optional.empty();
  }
}
