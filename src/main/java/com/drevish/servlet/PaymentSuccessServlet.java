package com.drevish.servlet;

import com.drevish.model.entity.Ticket;
import com.drevish.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/buy/success")
public class PaymentSuccessServlet extends HttpServlet {
  private static final String PAYMENT_SUCCESS_VIEW = "/WEB-INF/view/payment-success.jsp";

  private TicketService ticketService;
  //TODO: back-end validation
  //TODO: frond-end validation

  @Override
  public void init() throws ServletException {
    ticketService = (TicketService) getServletContext().getAttribute("ticketService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    Ticket ticket = (Ticket) session.getAttribute("ticket");
    ticketService.addTicket(ticket);
    session.removeAttribute("ticket");

    //TODO: fix encoding
    req.setAttribute("ticket", ticket);
    req.getRequestDispatcher(PAYMENT_SUCCESS_VIEW).forward(req, resp);
  }
}
