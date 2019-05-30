package com.drevish.model.repository;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {

  private static BasicDataSource ds = new BasicDataSource();

  static {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static {
    ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
    ds.setUsername("postgres");
    ds.setPassword("root");
    ds.setMinIdle(500);
    ds.setMaxIdle(1000);
    ds.setMaxOpenPreparedStatements(10);
    ds.setMaxWait(100);
  }

  private DBCPDataSource() {
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}