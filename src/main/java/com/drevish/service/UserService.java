package com.drevish.service;

import com.drevish.model.entity.User;
import com.drevish.model.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

public class UserService {
  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public Optional<User> login(String email, String password) {
    Optional<User> user = repository.findByEmail(email);

    if (!user.isPresent()) {
      // email not found
      return Optional.empty();
    }

    String md5 = DigestUtils.md5Hex(password).toUpperCase();
    if (!user.get().getPassword().equals(md5)) {
      // wrong password
      return Optional.empty();
    }

    return user;
  }

  public boolean register(String email, String password) {
    Optional<User> oldUser = repository.findByEmail(email);
    if(oldUser.isPresent()) {
      // email is not available
      return false;
    } else {
      // register
      String md5 = DigestUtils.md5Hex(password).toUpperCase();
      repository.addUser(email, md5, User.Role.USER);
      return true;
    }
  }
}
