package com.drevish.model.repository.impl;

import com.drevish.model.entity.Exhibit;
import com.drevish.model.entity.Showroom;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.model.repository.ExhibitRepository;
import com.drevish.model.repository.ShowroomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class ShowroomRepositoryImpl implements ShowroomRepository {
  private static final String FIND_ALL_SQL = "SELECT id, name FROM showroom";
  private static final String SELECT_BY_NAME_SQL = "SELECT id, name FROM showroom WHERE name = ?";

  private final ExhibitRepository exhibitRepository;

  @Override
  public List<Showroom> findAll() {
    RepositoryHelper<Showroom> helper = new RepositoryHelper<>();
    return helper.findAll(FIND_ALL_SQL, this::processResultSet, log);
  }

  @Override
  public Optional<Showroom> findByName(String name) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SELECT_BY_NAME_SQL);
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(processResultSet(rs));
      }
    } catch (SQLException e) {
      log.error(e.toString());
    }
    return Optional.empty();
  }

  private Showroom processResultSet(ResultSet rs) throws SQLException {
    long showroomId = rs.getLong("id");
    List<Exhibit> exhibits = exhibitRepository.findAllByShowroomId(showroomId);

    return new Showroom(rs.getLong("id"),
            rs.getString("name"), exhibits);
  }
}
