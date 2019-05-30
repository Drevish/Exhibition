package com.drevish.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
  String minDate() default "0000-00-00";

  boolean minDateIsToday() default false;

  String maxDate() default "9999-12-31";

  String message();
}
