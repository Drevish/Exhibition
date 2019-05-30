package com.drevish.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION_TYPE;
import static javax.servlet.RequestDispatcher.ERROR_MESSAGE;
import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Slf4j
@WebServlet("/errorHandler")
public class ErrorHandlerServlet extends HttpServlet {
  private static final String MAINTENANCE_VIEW = "/WEB-INF/view/maintenance.jsp";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String errorMessage = "Exception has occured: " +
            "Code: " + req.getAttribute(ERROR_STATUS_CODE) + ", " +
            "Type: " + req.getAttribute(ERROR_EXCEPTION_TYPE) + ", " +
            "Message: " + req.getAttribute(ERROR_MESSAGE);
    log.info(errorMessage);
    req.getRequestDispatcher(MAINTENANCE_VIEW).forward(req, resp);
  }
}
