package com.drevish.model.repository.impl;

import com.drevish.model.entity.Exhibit;
import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.model.repository.ExhibitRepository;
import com.drevish.model.repository.ExhibitionThemeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExhibitRepositoryImpl implements ExhibitRepository {
  private static final String FIND_BY_SHOWROOM_ID =
          "SELECT id, name, theme_id, showroom_id FROM exhibit WHERE showroom_id = ?";
  private final ExhibitionThemeRepository exhibitionThemeRepository;

  public ExhibitRepositoryImpl(ExhibitionThemeRepository exhibitionThemeRepository) {
    this.exhibitionThemeRepository = exhibitionThemeRepository;
  }

  @Override
  public List<Exhibit> findAllByShowroomId(Long showroomId) {
    List<Exhibit> exhibits = new ArrayList<>();

    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(FIND_BY_SHOWROOM_ID);
      stmt.setLong(1, showroomId);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        exhibits.add(processResultSet(rs));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return exhibits;
  }


  private Exhibit processResultSet(ResultSet rs) throws SQLException {
    long themeId = rs.getLong("theme_id");
    Optional<ExhibitionTheme> theme = exhibitionThemeRepository.findById(themeId);

    if (theme.isPresent()) {
      return new Exhibit(rs.getLong("id"),
              rs.getString("name"),
              theme.get());
    } else {
      //TODO: throw exception and log
      throw new SQLException("Exhibition theme not exists");
    }
  }
}
