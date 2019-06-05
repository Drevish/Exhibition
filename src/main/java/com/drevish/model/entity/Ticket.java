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

  @NotNull(message = "You should specify the exhibition date")
  @Date(minDateIsToday = true, message = "You can't order a ticket for the past")
  private LocalDate date;

  @NotNull(message = "There are no such exhibition theme")
  private ExhibitionTheme theme;

  private Payment payment;
}
