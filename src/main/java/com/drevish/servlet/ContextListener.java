package com.drevish.servlet;

import com.drevish.model.repository.ExhibitRepository;
import com.drevish.model.repository.ExhibitionThemeRepository;
import com.drevish.model.repository.ShowroomRepository;
import com.drevish.model.repository.UserRepository;
import com.drevish.model.repository.impl.ExhibitRepositoryImpl;
import com.drevish.model.repository.impl.ExhibitionThemeRepositoryImpl;
import com.drevish.model.repository.impl.ShowroomRepositoryImpl;
import com.drevish.model.repository.impl.UserRepositoryImpl;
import com.drevish.service.ShowroomService;
import com.drevish.service.UserService;
import com.drevish.service.impl.ShowroomServiceImpl;
import com.drevish.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    final ServletContext servletContext = sce.getServletContext();

    UserRepository userRepository = new UserRepositoryImpl();
    UserService userService = new UserServiceImpl(userRepository);
    servletContext.setAttribute("userService", userService);

    ExhibitionThemeRepository exhibitionThemeRepository = new ExhibitionThemeRepositoryImpl();
    ExhibitRepository exhibitRepository = new ExhibitRepositoryImpl(exhibitionThemeRepository);
    ShowroomRepository showroomRepository = new ShowroomRepositoryImpl(exhibitRepository);
    ShowroomService showroomService = new ShowroomServiceImpl(showroomRepository);
    servletContext.setAttribute("showroomService", showroomService);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}
