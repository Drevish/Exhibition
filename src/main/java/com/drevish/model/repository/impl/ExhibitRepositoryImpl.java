package com.drevish.model.repository.impl;

import com.drevish.model.entity.Exhibit;
import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.repository.ExhibitRepository;
import com.drevish.model.repository.ExhibitionThemeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class ExhibitRepositoryImpl implements ExhibitRepository {
  private static final String FIND_BY_SHOWROOM_ID =
          "SELECT id, name, theme_id, showroom_id FROM exhibit WHERE showroom_id = ?";

  private final ExhibitionThemeRepository exhibitionThemeRepository;


  @Override
  public List<Exhibit> findAllByShowroomId(Long showroomId) {
    RepositoryHelper<Exhibit> helper = new RepositoryHelper<>();
    return helper.findAllById(FIND_BY_SHOWROOM_ID, showroomId, this::processResultSet, log);
  }


  private Exhibit processResultSet(ResultSet rs) throws SQLException {
    long themeId = rs.getLong("theme_id");
    Optional<ExhibitionTheme> theme = exhibitionThemeRepository.findById(themeId);

    if (theme.isPresent()) {
      return new Exhibit(rs.getLong("id"),
              rs.getString("name"),
              theme.get());
    } else {
      throw new SQLException("Exhibition theme not exists");
    }
  }
}
