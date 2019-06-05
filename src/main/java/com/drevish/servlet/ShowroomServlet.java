package com.drevish.servlet;

import com.drevish.exception.NoSuchShowroomException;
import com.drevish.model.entity.Showroom;
import com.drevish.service.ShowroomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showroom/*")
public class ShowroomServlet extends HttpServlet {
  private static final String SHOWROOM_VIEW = "/WEB-INF/view/showroom.jsp";

  private ShowroomService showroomService;

  @Override
  public void init() {
    showroomService = (ShowroomService) getServletContext().getAttribute("showroomService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String[] pathInfo = req.getPathInfo().split("/");
    String showroomName = pathInfo[1];
    if (showroomName == null) {
      resp.sendRedirect("/");
    }

    try {
      Showroom showroom = showroomService.findByName(showroomName);
      req.setAttribute("showroom", showroom);
      req.getRequestDispatcher(SHOWROOM_VIEW).forward(req, resp);
    } catch (NoSuchShowroomException e) {
      resp.sendRedirect("/");
    }
  }
}
