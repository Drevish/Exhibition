package com.drevish.model.repository.impl;

import com.drevish.model.entity.Payment;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.model.repository.PaymentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class PaymentRepositoryImpl implements PaymentRepository {
  private static final String SELECT_BY_ID_SQL1 =
          "SELECT id, price, name, surname FROM payment WHERE id = ?";
  private static final String SELECT_BY_ID_SQL =
          "INSERT INTO payment (price, name, surname) VALUES (?, ?, ?)";

  @Override
  public Payment addPayment(Payment payment) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL,
              Statement.RETURN_GENERATED_KEYS);
      stmt.setLong(1, payment.getPrice());
      stmt.setString(2, payment.getName());
      stmt.setString(3, payment.getSurname());

      int affectedRows = stmt.executeUpdate();

      if (affectedRows != 0) {
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            return new Payment(generatedKeys.getLong(1),
                    payment.getPrice(),
                    payment.getName(),
                    payment.getSurname());
          } else {
            throw new SQLException("Creating failed, no ID obtained.");
          }
        }
      } else {
        throw new SQLException("Creating failed, no rows affected.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Optional<Payment> findById(Long id) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL1);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(mapResultSetToPayment(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  private Payment mapResultSetToPayment(ResultSet rs) throws SQLException {
    return new Payment(rs.getLong("id"),
            rs.getLong("price"),
            rs.getString("name"),
            rs.getString("surname"));
  }
}
