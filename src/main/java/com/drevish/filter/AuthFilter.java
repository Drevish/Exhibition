package com.drevish.filter;

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

@WebFilter(urlPatterns = {"/buy", "/buy/payment", "/buy/payment/success", "/tickets", "/ban"})
public class AuthFilter implements Filter {
  private static final String LOGIN_PAGE = "/login";

  @Override
  public void init(FilterConfig filterConfig) {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();

    if (FilterUtils.userIsAuthorized(session)) {
      chain.doFilter(request, response);
    } else {
      resp.sendRedirect(LOGIN_PAGE);
    }
  }

  @Override
  public void destroy() {

  }
}
