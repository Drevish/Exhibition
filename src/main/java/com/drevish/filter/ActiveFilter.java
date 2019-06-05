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

@WebFilter("/*")
public class ActiveFilter implements Filter {
  private static final String BAN_PAGE = "/ban";
  private static final String LOGOUT_PAGE = "/logout";

  @Override
  public void init(FilterConfig filterConfig) {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();

    String path = req.getRequestURI();
    if (path.equals(BAN_PAGE) || path.equals(LOGOUT_PAGE)) {
      chain.doFilter(request, response);
      return;
    }

    if (FilterUtils.userIsAuthorized(session)) {
      User user = (User) session.getAttribute("user");
      if (!user.getActive()) {
        resp.sendRedirect(BAN_PAGE);
      } else {
        chain.doFilter(request, response);
      }
    } else {
      chain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {

  }
}
