package com.drevish.model.repository;

import com.drevish.model.entity.ExhibitionTheme;

import java.util.Optional;

public interface ExhibitionThemeRepository {
  Optional<ExhibitionTheme> findById(Long id);
}
