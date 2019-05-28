package com.drevish.service;

import com.drevish.model.entity.User;

import java.util.Optional;

public interface UserService {
  Optional<User> login(String email, String password);

  boolean register(String email, String password);
}
