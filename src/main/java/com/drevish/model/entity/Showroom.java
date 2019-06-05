package com.drevish.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Showroom {
  private Long id;
  private String name;
  private List<Exhibit> exhibits;
}
