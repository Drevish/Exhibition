package com.drevish.service.impl;

import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.repository.ExhibitionThemeRepository;
import com.drevish.service.ExhibitionThemeService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ExhibitionThemeServiceImpl implements ExhibitionThemeService {
  private final ExhibitionThemeRepository repository;

  @Override
  public List<ExhibitionTheme> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<ExhibitionTheme> findById(Long id) {
    return repository.findById(id);
  }
}
