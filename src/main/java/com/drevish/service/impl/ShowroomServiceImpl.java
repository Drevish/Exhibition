package com.drevish.service.impl;

import com.drevish.model.entity.Showroom;
import com.drevish.model.repository.ShowroomRepository;
import com.drevish.service.ShowroomService;

import java.util.List;

public class ShowroomServiceImpl implements ShowroomService {
  private final ShowroomRepository repository;

  public ShowroomServiceImpl(ShowroomRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Showroom> findAll() {
    return repository.findAll();
  }
}
