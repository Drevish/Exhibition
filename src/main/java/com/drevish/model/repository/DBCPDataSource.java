package com.drevish.model.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DBCPDataSource {

  private static BasicDataSource ds = new BasicDataSource();

  static {
    try (InputStream in = DBCPDataSource.class.getClassLoader().
            getResourceAsStream("config.properties")) {
      Properties properties = new Properties();
      properties.load(in);

      Class.forName(properties.getProperty("db.driver.classname"));

      ds.setUrl(properties.getProperty("db.url"));
      ds.setUsername(properties.getProperty("db.username"));
      ds.setPassword(properties.getProperty("db.password"));
      ds.setMinIdle(500);
      ds.setMaxIdle(1000);
      ds.setMaxOpenPreparedStatements(10);
      ds.setMaxWait(100);
    } catch (Exception e) {
      log.error(e.toString());
    }
  }

  private DBCPDataSource() {
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
