package com.drevish.service.impl;

import com.drevish.exception.LoginException;
import com.drevish.exception.NoSuchUserException;
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
  private final static int ROWS_PER_PAGE = 10;

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
    User user = new User(null, email, password, User.Role.USER, true);
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

  @Override
  public User findById(Long id) throws NoSuchUserException {
    Optional<User> user = repository.findById(id);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new NoSuchUserException("No user with specified id exists");
    }
  }

  @Override
  public void updateUser(Long userId, User.Role role, boolean active) {
    User user = new User(userId, null, null, role, active);
    repository.updateUser(user);
  }

  @Override
  public List<User> findAllAtPage(int pageNumber) {
    int maxPage = getMaxPageNumber();
    if (pageNumber > maxPage) {
      pageNumber = maxPage;
    }
    if (pageNumber < 1) {
      pageNumber = 1;
    }

    int offset = (pageNumber - 1) * ROWS_PER_PAGE;
    return repository.fetch(offset, ROWS_PER_PAGE);
  }

  @Override
  public int getPagesCount() {
    return getMaxPageNumber();
  }

  private int getMaxPageNumber() {
    int rowsCount = repository.count();
    double pages = (double) rowsCount / ROWS_PER_PAGE;
    return (int) Math.ceil(pages);
  }
}
