package com.drevish.validation;

import com.drevish.validation.annotation.Date;
import com.drevish.validation.annotation.NotNull;
import com.drevish.validation.annotation.Pattern;
import com.drevish.validation.annotation.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TestEntity {
  @NotNull(message = "NotNull")
  @Size(min = 2, max = 5, message = "Size")
  @Pattern(regexp = "^[0-9]+$", message = "Pattern")
  private String string;

  @NotNull(message = "NotNull")
  @Date(minDateIsToday = true, message = "DateToday")
  private LocalDate minToday;

  @Date(minDate = "2000-01-01", maxDate = "2020-01-01", message = "Date")
  private LocalDate date;
}
