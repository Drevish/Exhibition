package com.drevish.servlet;

import com.drevish.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
  private static final String REGISTER_VIEW = "/WEB-INF/view/register.jsp";

  private UserService userService;

  @Override
  public void init() throws ServletException {
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
    System.out.println("Trying to register a new user: " + email + " " + password);

    if (userService.register(email, password)) {
      System.out.println("Registration successful");
      resp.sendRedirect("/login");
    } else {
      doGet(req, resp);
    }
  }
}
