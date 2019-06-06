package com.drevish.model.repository.impl;

import com.drevish.model.entity.Payment;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.model.repository.PaymentRepository;
import com.drevish.util.SqlQueries;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Slf4j
public class PaymentRepositoryImpl implements PaymentRepository {
  @Override
  public Optional<Payment> addPayment(Payment payment) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(SqlQueries.getValue("payment.INSERT_PAYMENT_SQL"),
              Statement.RETURN_GENERATED_KEYS);
      stmt.setLong(1, payment.getPrice());
      stmt.setString(2, payment.getName());
      stmt.setString(3, payment.getSurname());

      int affectedRows = stmt.executeUpdate();

      if (affectedRows != 0) {
        return getGeneratedPayment(stmt, payment);
      } else {
        throw new SQLException("Creating failed, no rows affected.");
      }
    } catch (SQLException e) {
      log.error(e.toString());
      return Optional.empty();
    }
  }

  private Optional<Payment> getGeneratedPayment(PreparedStatement stmt, Payment payment)
          throws SQLException {
    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
      if (generatedKeys.next()) {
        return Optional.of(new Payment(generatedKeys.getLong(1),
                payment.getPrice(),
                payment.getName(),
                payment.getSurname()));
      } else {
        throw new SQLException("Creating failed, no ID obtained.");
      }
    }
  }

  @Override
  public Optional<Payment> findById(Long id) {
    RepositoryHelper<Payment> helper = new RepositoryHelper<>();
    return helper.findById(SqlQueries.getValue("payment.SELECT_BY_ID_SQL"),
            id, this::mapResultSetToPayment, log);
  }

  private Payment mapResultSetToPayment(ResultSet rs) throws SQLException {
    return new Payment(rs.getLong("id"),
            rs.getLong("price"),
            rs.getString("name"),
            rs.getString("surname"));
  }
}
