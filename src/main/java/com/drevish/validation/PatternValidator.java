package com.drevish.validation;

import com.drevish.validation.annotation.Pattern;
import com.drevish.validation.error.ValidationError;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.regex.Matcher;

public class PatternValidator implements AnnotationValidator {
  @Override
  public Optional<ValidationError> validate(Object value, Annotation annotation, String fieldName) {
    Pattern anno = (Pattern) annotation;
    if (value instanceof String) {
      String s = (String) value;
      java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(anno.regexp(),
              java.util.regex.Pattern.UNICODE_CHARACTER_CLASS);
      Matcher matcher = pattern.matcher(s);

      if (!matcher.matches()) {
        return Optional.of(new ValidationError(anno.message(), fieldName));
      }
    }
    return Optional.empty();
  }
}
