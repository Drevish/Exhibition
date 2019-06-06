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

  @NotNull(message = "error.user.email.notnull")
  @Pattern(regexp = "^[A-Za-z0-9.]+@[A-Za-z0-9.]+.[A-Za-z0-9.]+$", message = "error.user.email.pattern")
  private String email;

  @NotNull(message = "error.user.password.notnull")
  @Size(min = 6, max = 40, message = "error.user.password.size")
  @Pattern(regexp = "^[A-Za-z0-9_]*$", message = "error.user.password.pattern")
  private String password;

  @NotNull(message = "error.user.role.notnull")
  private Role role;

  private Boolean active;

  public enum Role {
    USER, ADMIN, UNKNOWN
  }
}
