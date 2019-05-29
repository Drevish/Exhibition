package com.drevish.service.impl;

import com.drevish.model.entity.Ticket;
import com.drevish.model.entity.User;
import com.drevish.model.repository.TicketRepository;
import com.drevish.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
  private final TicketRepository repository;

  public TicketServiceImpl(TicketRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addTicket(Ticket ticket) {
    repository.addTicket(ticket);
  }

  @Override
  public List<Ticket> findByUser(User user) {
    return repository.findByUserId(user.getId());
  }
}
