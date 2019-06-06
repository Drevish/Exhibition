package com.drevish.model.entity;

import com.drevish.validation.annotation.Date;
import com.drevish.validation.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Ticket {
  private Long id;
  private User user;

  @NotNull(message = "error.ticket.date.notnull")
  @Date(minDateIsToday = true, message = "error.ticket.date.date")
  private LocalDate date;

  @NotNull(message = "error.ticket.theme.notnull")
  private ExhibitionTheme theme;

  private Payment payment;
}
