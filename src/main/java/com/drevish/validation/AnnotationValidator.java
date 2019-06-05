package com.drevish.validation;

import com.drevish.validation.error.ValidationError;

import java.lang.annotation.Annotation;
import java.util.Optional;

public interface AnnotationValidator {
  Optional<ValidationError> validate(Object value, Annotation annotation, String fieldName);
}
