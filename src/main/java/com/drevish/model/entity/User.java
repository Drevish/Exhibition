package com.drevish.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private Long id;
  private String email;
  private String password;
  private Role role;

  public static enum Role {
    USER, ADMIN, UNKNOWN
  }
}
