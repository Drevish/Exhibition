package com.drevish.model.repository;

import com.drevish.model.entity.Ticket;

import java.util.List;

public interface TicketRepository {
  void addTicket(Ticket ticket);

  List<Ticket> findByUserId(Long userId);
}
