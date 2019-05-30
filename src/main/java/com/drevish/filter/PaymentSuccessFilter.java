package com.drevish.filter;

import com.drevish.model.entity.Ticket;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/buy/payment/success")
public class PaymentSuccessFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();

    boolean paymentIsInSession = session.getAttribute("ticket") != null &&
            ((Ticket) session.getAttribute("ticket")).getPayment() != null;
    if (paymentIsInSession) {
      chain.doFilter(request, response);
    } else {
      resp.sendRedirect("/");
    }
  }

  @Override
  public void destroy() {

  }
}
