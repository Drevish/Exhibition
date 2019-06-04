package com.drevish.servlet.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ban")
public class BanServlet extends HttpServlet {
  private static final String BAN_VIEW = "/WEB-INF/view/ban.jsp";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher(BAN_VIEW).forward(req, resp);
  }
}
