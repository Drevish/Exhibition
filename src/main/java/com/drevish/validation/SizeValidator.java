package com.drevish.validation;

import com.drevish.validation.annotation.Size;
import com.drevish.validation.error.ValidationError;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class SizeValidator implements AnnotationValidator {
  @Override
  public Optional<ValidationError> validate(Object value, Annotation annotation, String fieldName) {
    Size anno = (Size) annotation;
    if (value instanceof String) {
      String s = (String) value;
      if (s.length() < anno.min() || s.length() > anno.max()) {
        return Optional.of(new ValidationError(anno.message(), fieldName));
      }
    }
    return Optional.empty();
  }
}
