package com.drevish.service.impl;

import com.drevish.model.entity.Ticket;
import com.drevish.model.entity.User;
import com.drevish.model.repository.TicketRepository;
import com.drevish.service.TicketService;
import com.drevish.validation.Validator;
import com.drevish.validation.error.Errors;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
  private final TicketRepository repository;

  @Override
  public Errors addTicket(Ticket ticket) {
    Errors errors = validate(ticket);
    if (!errors.hasErrors()) {
      repository.addTicket(ticket);
    }
    return errors;
  }

  @Override
  public List<Ticket> findByUser(User user) {
    return repository.findByUserId(user.getId());
  }

  @Override
  public Errors validate(Ticket ticket) {
    return Validator.validate(ticket);
  }
}
