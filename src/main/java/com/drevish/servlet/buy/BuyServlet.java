package com.drevish.servlet.buy;

import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.entity.Ticket;
import com.drevish.model.entity.User;
import com.drevish.service.ExhibitionThemeService;
import com.drevish.service.TicketService;
import com.drevish.validation.error.Errors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
  private static final String PAYMENT_URL = "/buy/payment";
  private static final String BUY_VIEW = "/WEB-INF/view/buy.jsp";

  private ExhibitionThemeService exhibitionThemeService;
  private TicketService ticketService;

  @Override
  public void init() {
    exhibitionThemeService =
            (ExhibitionThemeService) getServletContext().getAttribute("exhibitionThemeService");
    ticketService = (TicketService) getServletContext().getAttribute("ticketService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("themes", exhibitionThemeService.findAll());
    req.getRequestDispatcher(BUY_VIEW).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String dateString = req.getParameter("date");
    LocalDate date = parseDate(dateString).orElse(null);
    String themeId = req.getParameter("theme");
    ExhibitionTheme theme = getTheme(themeId).orElse(null);

    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");

    // add ticket to the session if valid and process to the payment view
    // or return an error otherwise
    Ticket ticket = new Ticket(null, user, date, theme, null);
    Errors errors = ticketService.validate(ticket);
    if (errors.hasErrors()) {
      req.setAttribute("errors", errors);
      doGet(req, resp);
    } else {
      session.setAttribute("ticket", ticket);
      resp.sendRedirect(PAYMENT_URL);
    }
  }

  private Optional<LocalDate> parseDate(String string) {
    try {
      return Optional.of(LocalDate.parse(string));
    } catch (DateTimeParseException e) {
      return Optional.empty();
    }
  }

  private Optional<ExhibitionTheme> getTheme(String themeId) {
    try {
      Long id = Long.parseLong(themeId);
      return exhibitionThemeService.findById(id);
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }
}
