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

  @NotNull(message = "Price can't be null")
  private Long price;

  @NotNull(message = "Name can't be null")
  @Size(min = 2, message = "Name is too short")
    @Pattern(regexp = "^\\w*$", message = "Invalid name")
  private String name;

  @NotNull(message = "Surname can't be null")
  @Size(min = 2, message = "Surname is too short")
  @Pattern(regexp = "^\\w*$", message = "Invalid surname")
  private String surname;
}
