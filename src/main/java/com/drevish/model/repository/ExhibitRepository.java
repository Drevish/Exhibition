package com.drevish.model.repository;

import com.drevish.model.entity.Exhibit;

import java.util.List;

public interface ExhibitRepository {
  List<Exhibit> findAllByShowroomId(Long showroomId);
}
