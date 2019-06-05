package com.drevish.service;

import com.drevish.exception.NoSuchShowroomException;
import com.drevish.model.entity.Showroom;

import java.util.List;

public interface ShowroomService {
  List<Showroom> findAll();

  Showroom findByName(String name) throws NoSuchShowroomException;
}
