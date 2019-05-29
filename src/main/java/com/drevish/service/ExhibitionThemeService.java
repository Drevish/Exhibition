package com.drevish.service;

import com.drevish.model.entity.ExhibitionTheme;

import java.util.List;
import java.util.Optional;

public interface ExhibitionThemeService {
  List<ExhibitionTheme> findAll();

  Optional<ExhibitionTheme> findById(Long id);
}
