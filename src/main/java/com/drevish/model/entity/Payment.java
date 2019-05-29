package com.drevish.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
  private Long id;
  private Long price;
  private String name;
  private String surname;
}
