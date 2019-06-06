package com.drevish.servlet.admin;

import com.drevish.exception.NoSuchUserException;
import com.drevish.model.entity.User;
import com.drevish.service.UserService;
import com.drevish.util.Views;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/admin/*")
public class UserServlet extends HttpServlet {
  private UserService userService;

  @Override
  public void init() {
    userService = (UserService) getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long userId = getUserId(req);
      User user = userService.findById(userId);

      req.setAttribute("user", user);
    } catch (NoSuchUserException exception) {
      log.info(exception.getMessage());
      req.setAttribute("exception", exception);
    }
    req.getRequestDispatcher(Views.getValue("view.user")).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long userId = getUserId(req);

      User.Role role = User.Role.valueOf(req.getParameter("role").toUpperCase());
      boolean active = Boolean.valueOf(req.getParameter("active"));
      log.info("Updating user with id " + userId + ": new role - " + role + ", new active: " + active);
      userService.updateUser(userId, role, active);
    } catch (NoSuchUserException exception) {
      log.info(exception.getMessage());
      req.setAttribute("exception", exception);
    }

    doGet(req, resp);
  }

  private Long getUserId(HttpServletRequest req) throws NoSuchUserException {
    String[] pathInfo = req.getPathInfo().split("/");
    try {
      return Long.parseLong(pathInfo[1]);
    } catch (NumberFormatException e) {
      throw new NoSuchUserException("Invalid id value");
    }
  }
}
