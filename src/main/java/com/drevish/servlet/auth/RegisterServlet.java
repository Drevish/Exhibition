package com.drevish.servlet.auth;

import com.drevish.exception.RegistrationException;
import com.drevish.service.UserService;
import com.drevish.servlet.ErrorAttribute;
import com.drevish.validation.error.Errors;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.drevish.servlet.ServletUtils.setRequestErrorAttribute;

@Slf4j
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
  private static final String PASSWORD_REPEAT_FIELD_NAME = "password_repeat";
  private static final String REGISTER_VIEW = "/WEB-INF/view/register.jsp";
  private UserService userService;

  @Override
  public void init() {
    userService = (UserService) getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher(REGISTER_VIEW).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    String passwordRepeat = req.getParameter(PASSWORD_REPEAT_FIELD_NAME);

    Optional<ErrorAttribute> error = checkPasswordsMatch(password, passwordRepeat);
    if (!error.isPresent()) {
      error = register(email, password);
    }

    if (error.isPresent()) {
      setRequestErrorAttribute(error.get(), req);
      doGet(req, resp);
    } else {
      log.info("A new user with email " + email + " has registered");
      resp.sendRedirect("/login");
    }
  }

  private Optional<ErrorAttribute> checkPasswordsMatch(String password, String passwordRepeat) {
    if (password == null || !password.equals(passwordRepeat)) {
      return Optional.of(new ErrorAttribute(PASSWORD_REPEAT_FIELD_NAME, "Passwords don't match!"));
    }
    return Optional.empty();
  }

  private Optional<ErrorAttribute> register(String email, String password) {
    try {
      Errors errors = userService.register(email, password);

      if (errors.hasErrors()) {
        return Optional.of(new ErrorAttribute("errors", errors));
      }
      return Optional.empty();
    } catch (RegistrationException e) {
      return Optional.of(new ErrorAttribute("exception", e));
    }
  }
}
