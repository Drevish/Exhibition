package com.drevish.model.repository.impl;

import com.drevish.model.entity.ExhibitionTheme;
import com.drevish.model.entity.Payment;
import com.drevish.model.entity.Ticket;
import com.drevish.model.entity.User;
import com.drevish.model.repository.DBCPDataSource;
import com.drevish.model.repository.ExhibitionThemeRepository;
import com.drevish.model.repository.PaymentRepository;
import com.drevish.model.repository.TicketRepository;
import com.drevish.model.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
  private static final String SELECT_BY_USER_ID =
          "SELECT id, user_id, date, theme_id, payment_id FROM ticket WHERE user_id = ?";
  private static final String INSERT_TICKET_SQL =
          "INSERT INTO ticket (user_id, date, theme_id, payment_id) VALUES (?, ?, ?, ?)";

  private final PaymentRepository paymentRepository;
  private final UserRepository userRepository;
  private final ExhibitionThemeRepository exhibitionThemeRepository;

  @Override
  public void addTicket(Ticket ticket) {
    try (Connection conn = DBCPDataSource.getConnection()) {
      Optional<Payment> payment = paymentRepository.addPayment(ticket.getPayment());
      if (!payment.isPresent()) {
        throw new SQLException("Can't add a payment " + ticket.getPayment());
      }

      PreparedStatement stmt = conn.prepareStatement(INSERT_TICKET_SQL);
      stmt.setLong(1, ticket.getUser().getId());
      stmt.setObject(2, ticket.getDate());
      stmt.setLong(3, ticket.getTheme().getId());
      stmt.setLong(4, payment.get().getId());

      stmt.execute();
    } catch (SQLException e) {
      log.error(e.toString());
    }
  }

  @Override
  public List<Ticket> findByUserId(Long userId) {
    RepositoryHelper<Ticket> helper = new RepositoryHelper<>();
    return helper.findAllById(SELECT_BY_USER_ID, userId, this::processResultSet, log);
  }

  private Ticket processResultSet(ResultSet rs) throws SQLException {
    long paymentId = rs.getLong("payment_id");
    Optional<Payment> payment = paymentRepository.findById(paymentId);

    long userId = rs.getLong("user_id");
    Optional<User> user = userRepository.findById(userId);

    long themeId = rs.getLong("theme_id");
    Optional<ExhibitionTheme> theme = exhibitionThemeRepository.findById(themeId);

    if (!payment.isPresent()) {
      throw new SQLException("Can't get payment with id that specified in ticket");
    }
    if (!user.isPresent()) {
      throw new SQLException("Can't get user with id that specified in ticket");
    }
    if (!theme.isPresent()) {
      throw new SQLException("Can't get exhibition theme with id that specified in ticket");
    }

    long id = rs.getLong("id");
    LocalDate date = LocalDate.parse(rs.getString("date"), DateTimeFormatter.ISO_LOCAL_DATE);

    return new Ticket(id, user.get(), date, theme.get(), payment.get());
  }
}
