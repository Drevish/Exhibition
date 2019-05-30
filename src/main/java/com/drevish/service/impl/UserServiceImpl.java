package com.drevish.service.impl;

import com.drevish.exception.LoginException;
import com.drevish.exception.RegistrationException;
import com.drevish.model.entity.User;
import com.drevish.model.repository.UserRepository;
import com.drevish.service.UserService;
import com.drevish.validation.Validator;
import com.drevish.validation.error.Errors;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository repository;

  @Override
  public User login(String email, String password) {
    Optional<User> user = repository.findByEmail(email);

    if (!user.isPresent()) {
      throw new LoginException("There are no user with specified email");
    }

    boolean passwordsMatch = md5Sum(password).equals(user.get().getPassword());
    if (!passwordsMatch) {
      throw new LoginException("Password is wrong");
    }

    return user.get();
  }

  private String md5Sum(String string) {
    return DigestUtils.md5Hex(string).toUpperCase();
  }

  @Override
  public Errors register(String email, String password) {
    User user = new User(null, email, password, User.Role.USER);
    return register(user);
  }

  private Errors register(User user) {
    Optional<User> existingUser = repository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
      throw new RegistrationException("User with specified email is already registered");
    } else {
      return validateAndRegister(user);
    }
  }

  private Errors validateAndRegister(User user) {
    Errors errors = Validator.validate(user);
    if (!errors.hasErrors()) {
      String md5 = md5Sum(user.getPassword());
      user.setPassword(md5);
      repository.addUser(user);
    }

    return errors;
  }

  @Override
  public List<User> findAll() {
    return repository.findAll();
  }
}
