package com.drevish.model.repository;

import com.drevish.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

  private static final String FIND_ALL_SQL = "SELECT id, email, password, role FROM users";
  private static final String SELECT_BY_EMAIL_SQL = "SELECT id, email, password, role FROM users WHERE email = ?";
  private static final String INSERT_USER_SQL = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";

  public List<User> findAll() {
    List<User> users = new ArrayList<>();

    try (Connection conn = DBCPDataSource.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(FIND_ALL_SQL);

      while (rs.next()) {
        users.add(
                new User(rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        User.Role.valueOf(rs.getString("role"))
                ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

  public Optional<User> findByEmail(String email) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SELECT_BY_EMAIL_SQL);
      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(new User(rs.getLong("id"),
                rs.getString("email"),
                rs.getString("password"),
                User.Role.valueOf(rs.getString("role"))
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public void addUser(String email, String password, User.Role role) {
    try(Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(INSERT_USER_SQL);
      stmt.setString(1, email);
      stmt.setString(2, password);
      stmt.setString(3, role.toString());

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
