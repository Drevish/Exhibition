package com.drevish.model.entity;

import com.drevish.validation.annotation.NotNull;
import com.drevish.validation.annotation.Pattern;
import com.drevish.validation.annotation.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
  private Long id;

  @NotNull(message = "error.payment.price.notnull")
  private Long price;

  @NotNull(message = "error.payment.name.notnull")
  @Size(min = 2, message = "error.payment.name.size")
  @Pattern(regexp = "^\\w*$", message = "error.payment.name.pattern")
  private String name;

  @NotNull(message = "error.payment.surname.notnull")
  @Size(min = 2, message = "error.payment.surname.size")
  @Pattern(regexp = "^\\w*$", message = "error.payment.surname.pattern")
  private String surname;
}
