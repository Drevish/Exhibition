package com.drevish.service.impl;

import com.drevish.exception.LoginException;
import com.drevish.exception.NoSuchUserException;
import com.drevish.exception.RegistrationException;
import com.drevish.model.entity.User;
import com.drevish.model.repository.UserRepository;
import com.drevish.validation.error.Errors;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
  @Mock
  private UserRepository repository;

  @InjectMocks
  private UserServiceImpl service;

  private String testEmail;
  private String testPassword;
  private User testUser;

  @Before
  public void before() {
    testEmail = "test@gmail.com";
    testPassword = "testtest";
    testUser = new User(null, testEmail, hash(testPassword), User.Role.USER, true);
  }

  @Test
  public void shouldRegisterUser() {
    when(repository.findByEmail(testEmail)).thenReturn(Optional.empty());
    service.register(testEmail, testPassword);
    verify(repository).addUser(testUser);
  }

  @Test
  public void shouldNotRegisterUserWithEngagedEmail() {
    when(repository.findByEmail(testEmail)).thenReturn(Optional.of(testUser));
    try {
      service.register(testEmail, testPassword);
      fail();
    } catch (RegistrationException ignored) {
    }
    verify(repository, never()).addUser(testUser);
  }

  @Test
  public void shouldNotRegisterUserWithInvalidData() {
    testEmail = "test";
    testUser.setEmail(testEmail);
    when(repository.findByEmail(testEmail)).thenReturn(Optional.empty());
    try {
      Errors errors = service.register(testEmail, testPassword);
      assertTrue(errors.hasErrors());
    } catch (RegistrationException ignored) {
    }
    verify(repository, never()).addUser(testUser);
  }

  @Test
  public void shouldLoginUser() {
    when(repository.findByEmail(testEmail)).thenReturn(Optional.of(testUser));
    User user = service.login(testEmail, testPassword);
    assertEquals(testUser, user);
  }

  @Test
  public void shouldNotLoginNotExistingUser() {
    when(repository.findByEmail(testEmail)).thenReturn(Optional.empty());
    try {
      service.login(testEmail, testPassword);
      fail();
    } catch (LoginException ignored) {
    }
    verify(repository, never()).findById(testUser.getId());
  }

  @Test
  public void shouldNotLoginUserWithWrongPassword() {
    when(repository.findByEmail(testEmail)).thenReturn(Optional.of(testUser));
    testUser.setPassword("test");
    try {
      service.login(testEmail, testPassword);
      fail();
    } catch (LoginException ignored) {
    }
  }

  @Test
  public void shouldReturnNegativePageAsFirst() {
    service.findAllAtPage(-10);
    verify(repository).fetch(0, 10);
  }

  @Test
  public void shouldReturnTooBigPageAsMax() {
    when(repository.count()).thenReturn(1);
    service.findAllAtPage(100);
    verify(repository).fetch(0, 10);
  }

  @Test
  public void findAll() {
    List<User> expected = new ArrayList<>();
    expected.add(testUser);
    when(repository.findAll()).thenReturn(expected);
    List<User> actual = service.findAll();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldFindById() {
    when(repository.findById(0L)).thenReturn(Optional.of(testUser));
    User user = service.findById(0L);
    assertEquals(testUser, user);
  }

  @Test
  public void shouldNotFindNotExistingById() {
    when(repository.findById(0L)).thenReturn(Optional.empty());
    try {
      User user = service.findById(0L);
      fail();
    } catch (NoSuchUserException ignored) {
    }
  }

  @Test
  public void shouldUpdateUser() {
    service.updateUser(0L, User.Role.USER, true);
    User expected = new User(0L, null, null, User.Role.USER, true);
    verify(repository).updateUser(expected);
  }

  @Test
  public void shouldReturnPagesCount() {
    when(repository.count()).thenReturn(25);
    int actual = service.getPagesCount();
    assertEquals(3, actual);
  }

  private String hash(String string) {
    return DigestUtils.md5Hex(string).toUpperCase();
  }
}