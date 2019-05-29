package com.drevish.servlet;

import com.drevish.model.repository.ExhibitRepository;
import com.drevish.model.repository.ExhibitionThemeRepository;
import com.drevish.model.repository.PaymentRepository;
import com.drevish.model.repository.ShowroomRepository;
import com.drevish.model.repository.TicketRepository;
import com.drevish.model.repository.UserRepository;
import com.drevish.model.repository.impl.ExhibitRepositoryImpl;
import com.drevish.model.repository.impl.ExhibitionThemeRepositoryImpl;
import com.drevish.model.repository.impl.PaymentRepositoryImpl;
import com.drevish.model.repository.impl.ShowroomRepositoryImpl;
import com.drevish.model.repository.impl.TicketRepositoryImpl;
import com.drevish.model.repository.impl.UserRepositoryImpl;
import com.drevish.service.ExhibitionThemeService;
import com.drevish.service.ShowroomService;
import com.drevish.service.TicketService;
import com.drevish.service.UserService;
import com.drevish.service.impl.ExhibitionThemeServiceImpl;
import com.drevish.service.impl.ShowroomServiceImpl;
import com.drevish.service.impl.TicketServiceImpl;
import com.drevish.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext servletContext = sce.getServletContext();

    UserRepository userRepository = new UserRepositoryImpl();
    ExhibitionThemeRepository exhibitionThemeRepository = new ExhibitionThemeRepositoryImpl();
    ExhibitRepository exhibitRepository = new ExhibitRepositoryImpl(exhibitionThemeRepository);
    ShowroomRepository showroomRepository = new ShowroomRepositoryImpl(exhibitRepository);
    PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    TicketRepository ticketRepository =
            new TicketRepositoryImpl(paymentRepository, userRepository, exhibitionThemeRepository);

    UserService userService = new UserServiceImpl(userRepository);
    servletContext.setAttribute("userService", userService);

    ExhibitionThemeService exhibitionThemeService =
            new ExhibitionThemeServiceImpl(exhibitionThemeRepository);
    servletContext.setAttribute("exhibitionThemeService", exhibitionThemeService);

    ShowroomService showroomService = new ShowroomServiceImpl(showroomRepository);
    servletContext.setAttribute("showroomService", showroomService);

    TicketService ticketService = new TicketServiceImpl(ticketRepository);
    servletContext.setAttribute("ticketService", ticketService);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}
