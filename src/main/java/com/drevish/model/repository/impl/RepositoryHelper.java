package com.drevish.model.repository.impl;

import com.drevish.model.repository.DBCPDataSource;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryHelper<T> {
  public RepositoryHelper() {
  }

  public List<T> findAllById(String sql, Long id, ResultSetMapper<T> mapper, Logger log) {
    List<T> list = new ArrayList<>();

    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        list.add(mapper.map(rs));
      }

    } catch (SQLException e) {
      log.error(e.toString());
    }

    return list;
  }

  public Optional<T> findById(String sql, Long id, ResultSetMapper<T> mapper, Logger log) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(mapper.map(rs));
      }
    } catch (SQLException e) {
      log.error(e.toString());
    }
    return Optional.empty();
  }

  public List<T> findAll(String sql, ResultSetMapper<T> mapper, Logger log) {
    List<T> list = new ArrayList<>();

    try (Connection conn = DBCPDataSource.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        list.add(mapper.map(rs));
      }
    } catch (SQLException e) {
      log.error(e.toString());
    }

    return list;
  }
}
