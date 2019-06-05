package com.drevish.service.impl;

import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.entity.Ticket;
import com.drevish.model.repository.TicketRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {
  @Mock
  private TicketRepository repository;

  @InjectMocks
  private TicketServiceImpl service;

  @Test
  public void shouldAddTicket() {
    Ticket correct = new Ticket(null, null, LocalDate.now().plusDays(10),
            new ExhibitionTheme(null, null), null);
    service.addTicket(correct);
    verify(repository).addTicket(correct);
  }

  @Test
  public void shouldNotAddInvalidTicket() {
    Ticket invalid = new Ticket(null, null, null, null, null);
    service.addTicket(invalid);
    verify(repository, never()).addTicket(invalid);
  }
}