package com.drevish.service.impl;

import com.drevish.model.entity.Showroom;
import com.drevish.model.repository.ShowroomRepository;
import com.drevish.service.ShowroomService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShowroomServiceImpl implements ShowroomService {
  private final ShowroomRepository repository;

  @Override
  public List<Showroom> findAll() {
    return repository.findAll();
  }
}
