package com.drevish.model.repository;

import com.drevish.model.entity.Showroom;

import java.util.List;

public interface ShowroomRepository {
  List<Showroom> findAll();
}
