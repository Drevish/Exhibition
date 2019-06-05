package com.drevish.servlet.admin;

import com.drevish.model.entity.User;
import com.drevish.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
  private static final String ADMIN_VIEW = "/WEB-INF/view/admin.jsp";

  private UserService userService;

  @Override
  public void init() {
    userService = (UserService) getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Optional<Integer> pageNumber = getPageNumber(req);
    int page = pageNumber.orElse(1);
    List<User> users = userService.findAllAtPage(page);
    int pagesNumber = userService.getPagesCount();

    req.setAttribute("users", users);
    req.setAttribute("page", page);
    req.setAttribute("pagesNumber", pagesNumber);
    req.getRequestDispatcher(ADMIN_VIEW).forward(req, resp);
  }

  private Optional<Integer> getPageNumber(HttpServletRequest req) {
    String page = req.getParameter("page");
    Integer pageNumber = null;
    if (page != null) {
      try {
        pageNumber = Integer.parseInt(page);
      } catch (NumberFormatException ignored) {
      }
    }
    return Optional.ofNullable(pageNumber);
  }
}
