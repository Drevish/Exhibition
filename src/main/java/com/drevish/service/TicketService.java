package com.drevish.service;

import com.drevish.model.entity.Ticket;
import com.drevish.model.entity.User;

import java.util.List;

public interface TicketService {
  void addTicket(Ticket ticket);

  List<Ticket> findByUser(User user);
}
