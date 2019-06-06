package com.drevish.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class SqlQueries {
  private static Map<String, String> queries = PropertyHandler.getProperties("sql.properties");

  private SqlQueries() {
  }

  public static String getValue(String key) {
    return queries.get(key);
  }
}
