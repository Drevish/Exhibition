package com.drevish.model.entity;

import com.drevish.validation.annotation.NotNull;
import com.drevish.validation.annotation.Pattern;
import com.drevish.validation.annotation.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private Long id;

  @NotNull(message = "Email can't be null")
  @Pattern(regexp = "^[A-Za-z0-9.]+@[A-Za-z0-9.]+.[A-Za-z0-9.]+$", message = "Invalid email")
  private String email;

  @NotNull(message = "Password can't be null")
  @Size(min = 6, max = 40, message = "Password should contain at least 6 and at most 40 symbols")
  @Pattern(regexp = "^[A-Za-z0-9_]*$", message = "Password can contain only latin symbols, numbers and _")
  private String password;

  @NotNull(message = "Role can't be null")
  private Role role;

  private Boolean active;

  public enum Role {
    USER, ADMIN, UNKNOWN
  }
}
