package com.drevish.service;

import com.drevish.model.entity.User;
import com.drevish.validation.error.Errors;

import java.util.List;

public interface UserService {
  User login(String email, String password);

  Errors register(String email, String password);

  List<User> findAll();
}
