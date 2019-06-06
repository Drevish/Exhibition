package com.drevish.servlet;

import com.drevish.model.entity.Showroom;
import com.drevish.service.ShowroomService;
import com.drevish.util.Views;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {
  private ShowroomService showroomService;

  @Override
  public void init() {
    showroomService = (ShowroomService) getServletContext().getAttribute("showroomService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Showroom> showrooms = showroomService.findAll();
    req.setAttribute("showrooms", showrooms);
    req.getRequestDispatcher(Views.getValue("view.home")).forward(req, resp);
  }
}
