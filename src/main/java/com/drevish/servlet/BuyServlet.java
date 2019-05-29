package com.drevish.servlet;

import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.entity.Ticket;
import com.drevish.model.entity.User;
import com.drevish.service.ExhibitionThemeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
  private static final String BUY_VIEW = "/WEB-INF/view/buy.jsp";

  private ExhibitionThemeService exhibitionThemeService;

  @Override
  public void init() throws ServletException {
    exhibitionThemeService =
            (ExhibitionThemeService) getServletContext().getAttribute("exhibitionThemeService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("themes", exhibitionThemeService.findAll());
    req.getRequestDispatcher(BUY_VIEW).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String dateString = req.getParameter("date");
    LocalDate date = LocalDate.parse(dateString);
    String themeId = req.getParameter("theme");
    ExhibitionTheme theme = exhibitionThemeService.findById(Long.parseLong(themeId)).get();

    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");

    Ticket ticket = new Ticket(null, user, date, theme, null);
    session.setAttribute("ticket", ticket);

    resp.sendRedirect("/buy/payment");
  }
}
