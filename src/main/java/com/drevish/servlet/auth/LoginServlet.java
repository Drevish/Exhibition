package com.drevish.servlet.auth;

import com.drevish.exception.LoginException;
import com.drevish.model.entity.User;
import com.drevish.service.UserService;
import com.drevish.util.Views;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private UserService userService;

  @Override
  public void init() {
    userService = (UserService) getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher(Views.getValue("view.login")).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");

    try {
      User user = userService.login(email, password);

      log.info("User with email " + email + " has successfully logged in");
      HttpSession session = req.getSession();
      session.setAttribute("user", user);
      resp.sendRedirect("/");
    } catch (LoginException e) {
      req.setAttribute("exception", e);
      doGet(req, resp);
    }
  }
}
