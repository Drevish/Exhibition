package com.drevish.servlet.buy;

import com.drevish.model.entity.Payment;
import com.drevish.model.entity.Ticket;
import com.drevish.service.PaymentService;
import com.drevish.util.Views;
import com.drevish.validation.error.Errors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/buy/payment")
public class PaymentServlet extends HttpServlet {
  private static final int MINIMUM_TICKET_PRICE = 10;
  private static final String PAYMENT_SUCCESS_URL = "/buy/payment/success";

  private PaymentService paymentService;

  @Override
  public void init() {
    paymentService = (PaymentService) getServletContext().getAttribute("paymentService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("minPrice", MINIMUM_TICKET_PRICE);
    req.getRequestDispatcher(Views.getValue("view.payment")).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    Long price = parsePrice(req.getParameter("price")).orElse(null);

    // add payment to the ticket if valid and process to the success view
    // or return an error otherwise
    Payment payment = new Payment(null, price, name, surname);
    Errors errors = paymentService.validate(payment);
    if (errors.hasErrors()) {
      req.setAttribute("errors", errors);
      doGet(req, resp);
    } else {
      HttpSession session = req.getSession();
      Ticket ticket = (Ticket) session.getAttribute("ticket");
      ticket.setPayment(payment);
      resp.sendRedirect(PAYMENT_SUCCESS_URL);
    }
  }

  private Optional<Long> parsePrice(String string) {
    try {
      return Optional.of(Long.parseLong(string));
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }
}
