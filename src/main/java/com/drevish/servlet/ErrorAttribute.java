package com.drevish.servlet;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorAttribute {
  private String name;
  private Object value;
}

