package com.drevish.servlet;

import com.drevish.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/rest")
public class RestServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = new User(4L, "tst@aksa.cq", "qqqq", User.Role.ADMIN);
    resp.getWriter().write("{id: 4}");
  }
}
