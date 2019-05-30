package com.drevish.model.repository.impl;

import com.drevish.model.entity.Exhibit;
import com.drevish.model.entity.Showroom;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.model.repository.ExhibitRepository;
import com.drevish.model.repository.ShowroomRepository;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ShowroomRepositoryImpl implements ShowroomRepository {
  private static final String FIND_ALL_SQL = "SELECT id, name FROM showroom";

  private final ExhibitRepository exhibitRepository;

  @Override
  public List<Showroom> findAll() {
    List<Showroom> showrooms = new ArrayList<>();

    try (Connection conn = DBCPDataSource.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(FIND_ALL_SQL);

      while (rs.next()) {
        showrooms.add(processResultSet(rs));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return showrooms;
  }

  private Showroom processResultSet(ResultSet rs) throws SQLException {
    long showroomId = rs.getLong("id");
    List<Exhibit> exhibits = exhibitRepository.findAllByShowroomId(showroomId);

    return new Showroom(rs.getLong("id"),
            rs.getString("name"), exhibits);
  }
}
