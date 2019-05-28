package com.drevish.servlet;

import com.drevish.model.entity.User;
import com.drevish.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private static final String LOGIN_VIEW = "/WEB-INF/view/login.jsp";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher(LOGIN_VIEW).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    System.out.println("Trying to login: " + email + " " + password);

    UserService service = (UserService) getServletContext().getAttribute("userService");
    Optional<User> user = service.login(email, password);
    if (!user.isPresent()) {
      doGet(req, resp);
    } else {
      System.out.println("Login successful");
      HttpSession session = req.getSession();
      session.setAttribute("user", user.get());
      resp.sendRedirect("/");
    }
  }
}
