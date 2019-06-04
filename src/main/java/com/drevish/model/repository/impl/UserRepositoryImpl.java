package com.drevish.model.repository.impl;

import com.drevish.model.entity.User;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.model.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
  private static final String FIND_ALL_SQL =
          "SELECT id, email, password, role, active FROM users ORDER BY id";
  private static final String SELECT_BY_EMAIL_SQL =
          "SELECT id, email, password, role, active FROM users WHERE email = ?";
  private static final String SELECT_BY_ID_SQL =
          "SELECT id, email, password, role, active FROM users WHERE id = ?";
  private static final String INSERT_USER_SQL =
          "INSERT INTO users (email, password, role, active) VALUES (?, ?, ?, ?)";
  private static final String UPDATE_USER_SQL =
          "UPDATE users SET role = ?, active = ? WHERE id = ?";
  private static final String FETCH_USER_SQL =
          "SELECT id, email, password, role, active FROM users ORDER BY id " +
                  "OFFSET ? ROWS FETCH FIRST ? ROW ONLY";
  private static final String COUNT_SQL = "SELECT COUNT(*) FROM users";

  @Override
  public List<User> findAll() {
    List<User> users = new ArrayList<>();

    try (Connection conn = DBCPDataSource.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(FIND_ALL_SQL);

      while (rs.next()) {
        users.add(mapResultSetToUser(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

  @Override
  public Optional<User> findByEmail(String email) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SELECT_BY_EMAIL_SQL);
      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(mapResultSetToUser(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<User> findById(Long id) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(mapResultSetToUser(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public void addUser(User user) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(INSERT_USER_SQL);
      stmt.setString(1, user.getEmail());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getRole().toString());
      stmt.setBoolean(4, user.getActive());

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateUser(User user) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(UPDATE_USER_SQL);
      stmt.setString(1, user.getRole().toString());
      stmt.setBoolean(2, user.getActive());
      stmt.setLong(3, user.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<User> fetch(int offset, int rowsCount) {
    List<User> users = new ArrayList<>();

    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(FETCH_USER_SQL);
      stmt.setInt(1, offset);
      stmt.setInt(2, rowsCount);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        users.add(mapResultSetToUser(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

  @Override
  public int count() {
    try (Connection conn = DBCPDataSource.getConnection()) {
      Statement stmt = conn.createStatement(
              ResultSet.TYPE_SCROLL_SENSITIVE,
              ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery(COUNT_SQL);
      return rs.last() ? rs.getInt("count") : 0;
    } catch (SQLException e) {
      e.printStackTrace();
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
