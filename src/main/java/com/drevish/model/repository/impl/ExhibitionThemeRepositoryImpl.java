package com.drevish.model.repository.impl;

import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.model.repository.ExhibitionThemeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExhibitionThemeRepositoryImpl implements ExhibitionThemeRepository {
  public static final String FIND_ALL_SQL = "SELECT id, name FROM exhibition_theme";
  private static final String SELECT_BY_ID_SQL = "SELECT id, name FROM exhibition_theme WHERE id = ?";

  @Override
  public Optional<ExhibitionTheme> findById(Long id) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(mapResultSetExhibitionTheme(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public List<ExhibitionTheme> findAll() {
    List<ExhibitionTheme> themes = new ArrayList<>();

    try (Connection conn = DBCPDataSource.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(FIND_ALL_SQL);

      while (rs.next()) {
        themes.add(mapResultSetExhibitionTheme(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return themes;
  }

  private ExhibitionTheme mapResultSetExhibitionTheme(ResultSet rs) throws SQLException {
    return new ExhibitionTheme(rs.getLong("id"),
            rs.getString("name"));
  }

}
