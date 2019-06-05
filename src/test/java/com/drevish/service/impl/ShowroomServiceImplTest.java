package com.drevish.service.impl;

import com.drevish.model.entity.Showroom;
import com.drevish.model.repository.ShowroomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowroomServiceImplTest {
  @Mock
  private ShowroomRepository repository;

  @InjectMocks
  private ShowroomServiceImpl service;

  @Test
  public void shouldReturnByName() {
    String name = "test";
    Showroom expected = new Showroom(null, name, null);
    when(repository.findByName(name)).thenReturn(Optional.of(expected));
    Showroom actual = service.findByName(name);
    assertEquals(expected, actual);
  }
}