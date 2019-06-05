package com.drevish.model.repository;

import com.drevish.model.entity.Showroom;

import java.util.List;
import java.util.Optional;

public interface ShowroomRepository {
  List<Showroom> findAll();

  Optional<Showroom> findByName(String name);
}
