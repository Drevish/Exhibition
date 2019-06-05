package com.drevish.service.impl;

import com.drevish.exception.NoSuchShowroomException;
import com.drevish.model.entity.Showroom;
import com.drevish.model.repository.ShowroomRepository;
import com.drevish.service.ShowroomService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ShowroomServiceImpl implements ShowroomService {
  private final ShowroomRepository repository;

  @Override
  public List<Showroom> findAll() {
    return repository.findAll();
  }

  @Override
  public Showroom findByName(String name) throws NoSuchShowroomException {
    Optional<Showroom> showroom = repository.findByName(name);
    if (showroom.isPresent()) {
      return showroom.get();
    } else {
      throw new NoSuchShowroomException("No showroom with such name exists");
    }
  }
}
