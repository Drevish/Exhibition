package com.drevish.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Ticket {
  private Long id;
  private User user;
  private LocalDate date;
  private ExhibitionTheme theme;
  private Payment payment;
}
