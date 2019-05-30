package com.drevish.servlet;

import com.drevish.model.entity.User;
import com.drevish.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
  private static final String ADMIN_VIEW = "/WEB-INF/view/admin.jsp";

  private UserService userService;

  @Override
  public void init() {
    userService = (UserService) getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<User> users = userService.findAll();
    req.setAttribute("users", users);
    req.getRequestDispatcher(ADMIN_VIEW).forward(req, resp);
  }
}
