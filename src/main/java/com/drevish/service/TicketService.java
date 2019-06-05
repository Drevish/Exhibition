package com.drevish.service;

import com.drevish.model.entity.Ticket;
import com.drevish.model.entity.User;
import com.drevish.validation.error.Errors;

import java.util.List;

public interface TicketService {
  Errors addTicket(Ticket ticket);

  List<Ticket> findByUser(User user);

  Errors validate(Ticket ticket);
}
