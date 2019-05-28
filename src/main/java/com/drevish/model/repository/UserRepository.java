package com.drevish.model.repository;

import com.drevish.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  List<User> findAll();

  Optional<User> findByEmail(String email);

  void addUser(String email, String password, User.Role role);
}
