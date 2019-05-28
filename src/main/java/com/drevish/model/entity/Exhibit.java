package com.drevish.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exhibit {
  private Long id;
  private String name;
  private ExhibitionTheme theme;
}
