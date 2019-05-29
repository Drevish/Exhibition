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
  private static final String FIND_ALL_SQL = "SELECT id, email, password, role FROM users";
  private static final String SELECT_BY_EMAIL_SQL = "SELECT id, email, password, role FROM users WHERE email = ?";
  private static final String SELECT_BY_ID_SQL = "SELECT id, email, password, role FROM users WHERE id = ?";
  private static final String INSERT_USER_SQL = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";

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

  private User mapResultSetToUser(ResultSet rs) throws SQLException {
    return new User(rs.getLong("id"),
            rs.getString("email"),
            rs.getString("password"),
            User.Role.valueOf(rs.getString("role")));
  }

  @Override
  public void addUser(User user) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(INSERT_USER_SQL);
      stmt.setString(1, user.getEmail());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getRole().toString());

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
