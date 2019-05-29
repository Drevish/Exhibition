package com.drevish.filter;

import com.drevish.model.entity.User;

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

@WebFilter(urlPatterns = {"/buy", "/tickets"})
public class AuthFilter implements Filter {
  private static final String LOGIN_PAGE = "/login";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void destroy() {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();

    if (session != null && session.getAttribute("user") != null) {
      User user = (User) session.getAttribute("user");
      if (!user.getRole().equals(User.Role.UNKNOWN)) {
        chain.doFilter(request, response);
      } else {
        resp.sendRedirect(AuthFilter.LOGIN_PAGE);
      }
    } else {
      resp.sendRedirect(LOGIN_PAGE);
    }
  }
}
