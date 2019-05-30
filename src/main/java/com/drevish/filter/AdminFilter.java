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

@WebFilter("/admin")
public class AdminFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();

    boolean userIsAdmin = session != null &&
            session.getAttribute("user") != null &&
            ((User) session.getAttribute("user"))
                    .getRole().equals(User.Role.ADMIN);
    if (userIsAdmin) {
      chain.doFilter(request, response);
    } else {
      resp.sendRedirect("/");
    }
  }

  @Override
  public void destroy() {

  }
}
