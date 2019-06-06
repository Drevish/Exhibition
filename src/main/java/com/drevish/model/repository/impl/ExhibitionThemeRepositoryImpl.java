package com.drevish.model.repository.impl;

import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.model.repository.ExhibitionThemeRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ExhibitionThemeRepositoryImpl implements ExhibitionThemeRepository {
  private static final String FIND_ALL_SQL = "SELECT id, name FROM exhibition_theme";
  private static final String SELECT_BY_ID_SQL = "SELECT id, name FROM exhibition_theme WHERE id = ?";

  @Override
  public Optional<ExhibitionTheme> findById(Long id) {
    RepositoryHelper<ExhibitionTheme> helper = new RepositoryHelper<>();
    return helper.findById(SELECT_BY_ID_SQL, id, this::mapResultSetExhibitionTheme, log);
  }

  @Override
  public List<ExhibitionTheme> findAll() {
    RepositoryHelper<ExhibitionTheme> helper = new RepositoryHelper<>();
    return helper.findAll(FIND_ALL_SQL, this::mapResultSetExhibitionTheme, log);
  }

  private ExhibitionTheme mapResultSetExhibitionTheme(ResultSet rs) throws SQLException {
    return new ExhibitionTheme(rs.getLong("id"),
            rs.getString("name"));
  }
}
