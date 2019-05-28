package com.drevish.servlet;

import com.drevish.model.entity.Showroom;
import com.drevish.service.ShowroomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {

  private static final String HOME_JSP = "/WEB-INF/view/index.jsp";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ShowroomService service = (ShowroomService) getServletContext().getAttribute("showroomService");
    List<Showroom> showrooms = service.findAll();
    req.setAttribute("showrooms", showrooms);

    RequestDispatcher requestDispatcher = req.getRequestDispatcher(HOME_JSP);
    requestDispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect("/");
  }
}
