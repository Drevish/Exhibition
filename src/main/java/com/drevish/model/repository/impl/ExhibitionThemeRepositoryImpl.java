package com.drevish.model.repository.impl;

import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.repository.ExhibitionThemeRepository;
import com.drevish.util.SqlQueries;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ExhibitionThemeRepositoryImpl implements ExhibitionThemeRepository {
  @Override
  public Optional<ExhibitionTheme> findById(Long id) {
    RepositoryHelper<ExhibitionTheme> helper = new RepositoryHelper<>();
    return helper.findById(SqlQueries.getValue("exhibitiontheme.SELECT_BY_ID_SQL"), id, this::mapResultSetExhibitionTheme, log);
  }

  @Override
  public List<ExhibitionTheme> findAll() {
    RepositoryHelper<ExhibitionTheme> helper = new RepositoryHelper<>();
    return helper.findAll(SqlQueries.getValue("exhibitiontheme.FIND_ALL_SQL"), this::mapResultSetExhibitionTheme, log);
  }

  private ExhibitionTheme mapResultSetExhibitionTheme(ResultSet rs) throws SQLException {
    return new ExhibitionTheme(rs.getLong("id"),
            rs.getString("name"));
  }
}
