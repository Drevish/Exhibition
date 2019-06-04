package com.drevish.service;

import com.drevish.exception.NoSuchUserException;
import com.drevish.model.entity.User;
import com.drevish.validation.error.Errors;

import java.util.List;
import java.util.Optional;

public interface UserService {
  User login(String email, String password);

  Errors register(String email, String password);

  List<User> findAll();

  User findById(Long id) throws NoSuchUserException;

  void updateUser(Long userId, User.Role role, boolean active);

  List<User> findAllAtPage(int pageNumber);

  int getPagesCount();
}
