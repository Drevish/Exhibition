package com.drevish.service.impl;

import com.drevish.model.entity.User;
import com.drevish.model.repository.UserRepository;
import com.drevish.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
  private final UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
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

  @Override
  public boolean register(String email, String password) {
    Optional<User> oldUser = repository.findByEmail(email);
    if (oldUser.isPresent()) {
      // email is not available
      return false;
    } else {
      // register
      String md5 = DigestUtils.md5Hex(password).toUpperCase();
      User user = new User(null, email, md5, User.Role.USER);
      repository.addUser(user);
      return true;
    }
  }

  @Override
  public List<User> findAll() {
    return repository.findAll();
  }
}
