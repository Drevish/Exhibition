package com.drevish.model.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetMapper<T> {
  T map(ResultSet rs) throws SQLException;
}
