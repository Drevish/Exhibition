package com.drevish.validation;

import com.drevish.validation.annotation.Date;
import com.drevish.validation.annotation.NotNull;
import com.drevish.validation.annotation.Pattern;
import com.drevish.validation.annotation.Size;
import com.drevish.validation.error.Errors;
import com.drevish.validation.error.ValidationError;
import lombok.AllArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class Validator {
  private static Map<Class<? extends Annotation>,
          AnnotationValidator> validatorsMap = new HashMap<>();

  static {
    validatorsMap.put(NotNull.class, new NotNullValidator());
    validatorsMap.put(Date.class, new DateValidator());
    validatorsMap.put(Size.class, new SizeValidator());
    validatorsMap.put(Pattern.class, new PatternValidator());
  }

  public static <T> Errors validate(T instance) {
    Errors errors = new Errors();

    Class<?> clazz = instance.getClass();
    Field[] fields = clazz.getDeclaredFields();
    Arrays.stream(fields).forEach(x -> errors.addErrors(x.getName(), validateField(x, instance)));

    return errors;
  }

  private static List<ValidationError> validateField(Field field, Object instance) {
    List<ValidationError> errors = new ArrayList<>();
    String fieldName = field.getName();
    Object value = getFieldValue(field, instance);

    validatorsMap.forEach((annoClass, validator) -> {
      if (field.isAnnotationPresent(annoClass)) {
        validator.validate(value, field.getAnnotation(annoClass), fieldName)
                .ifPresent(errors::add);
      }
    });

    return errors;
  }

  private static Object getFieldValue(Field field, Object instance) {
    field.setAccessible(true);
    Object value = null;
    try {
      value = field.get(instance);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return value;
  }
}
