package com.drevish.servlet;


import com.drevish.model.entity.Ticket;
import com.drevish.model.entity.User;
import com.drevish.service.TicketService;
import com.drevish.util.Views;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tickets")
public class TicketsServlet extends HttpServlet {
  private TicketService ticketService;

  @Override
  public void init() {
    ticketService = (TicketService) getServletContext().getAttribute("ticketService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");
    List<Ticket> tickets = ticketService.findByUser(user);
    req.setAttribute("tickets", tickets);
    req.getRequestDispatcher(Views.getValue("view.tickets")).forward(req, resp);
  }
}
