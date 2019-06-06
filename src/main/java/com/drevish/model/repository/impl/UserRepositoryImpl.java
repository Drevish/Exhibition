package com.drevish.model.repository.impl;

import com.drevish.model.entity.User;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.util.SqlQueries;
import com.drevish.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {
  @Override
  public List<User> findAll() {
    RepositoryHelper<User> helper = new RepositoryHelper<>();
    return helper.findAll(SqlQueries.getValue("user.FIND_ALL_SQL"), this::mapResultSetToUser, log);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SqlQueries.getValue("user.SELECT_BY_EMAIL_SQL"));
      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(mapResultSetToUser(rs));
      }
    } catch (SQLException e) {
      log.error(e.toString());
    }
    return Optional.empty();
  }

  @Override
  public Optional<User> findById(Long id) {
    RepositoryHelper<User> helper = new RepositoryHelper<>();
    return helper.findById(SqlQueries.getValue("user.SELECT_BY_ID_SQL"),
            id, this::mapResultSetToUser, log);
  }

  @Override
  public void addUser(User user) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SqlQueries.getValue("user.INSERT_USER_SQL"));
      stmt.setString(1, user.getEmail());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getRole().toString());
      stmt.setBoolean(4, user.getActive());

      stmt.execute();
    } catch (SQLException e) {
      log.error(e.toString());
    }
  }

  @Override
  public void updateUser(User user) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SqlQueries.getValue("user.UPDATE_USER_SQL"));
      stmt.setString(1, user.getRole().toString());
      stmt.setBoolean(2, user.getActive());
      stmt.setLong(3, user.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      log.error(e.toString());
    }
  }

  @Override
  public List<User> fetch(int offset, int rowsCount) {
    List<User> users = new ArrayList<>();

    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SqlQueries.getValue("user.FETCH_USER_SQL"));
      stmt.setInt(1, offset);
      stmt.setInt(2, rowsCount);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        users.add(mapResultSetToUser(rs));
      }
    } catch (SQLException e) {
      log.error(e.toString());
    }

    return users;
  }

  @Override
  public int count() {
    try (Connection conn = DBCPDataSource.getConnection()) {
      Statement stmt = conn.createStatement(
              ResultSet.TYPE_SCROLL_SENSITIVE,
              ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery(SqlQueries.getValue("user.COUNT_SQL"));
      return rs.last() ? rs.getInt("count") : 0;
    } catch (SQLException e) {
      log.error(e.toString());
      return 0;
    }
  }

  private User mapResultSetToUser(ResultSet rs) throws SQLException {
    return new User(rs.getLong("id"),
            rs.getString("email"),
            rs.getString("password"),
            User.Role.valueOf(rs.getString("role")),
            rs.getBoolean("active"));
  }
}
