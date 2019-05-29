package com.drevish.servlet;

import com.drevish.model.entity.Payment;
import com.drevish.model.entity.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/buy/payment")
public class PaymentServlet extends HttpServlet {
  private static final String PAYMENT_VIEW = "/WEB-INF/view/payment.jsp";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //TODO: add filter that checks if there are ticket in session
    req.setAttribute("minPrice", 10);
    req.getRequestDispatcher(PAYMENT_VIEW).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    Long price = Long.parseLong(req.getParameter("price"));
    Payment payment = new Payment(null, price, name, surname);

    HttpSession session = req.getSession();
    Ticket ticket = (Ticket) session.getAttribute("ticket");
    ticket.setPayment(payment);
    System.out.println(ticket.toString());
    resp.sendRedirect("/buy/success");
  }
}
