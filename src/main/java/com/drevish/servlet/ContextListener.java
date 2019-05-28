package com.drevish.servlet;

import com.drevish.model.repository.UserRepository;
import com.drevish.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
  private UserService userService;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    final ServletContext servletContext = sce.getServletContext();
    UserRepository userRepository = new UserRepository();
    userService = new UserService(userRepository);

    servletContext.setAttribute("userService", userService);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}
