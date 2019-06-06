package com.drevish.util;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class PropertyHandler {
  private PropertyHandler() {
  }

  public static Map<String, String> getProperties(String fileName) {
    Map<String, String> map = new HashMap<>();

    try (InputStream in = Views.class.getClassLoader().
            getResourceAsStream(fileName)) {
      Properties properties = new Properties();
      properties.load(in);
      properties.forEach((key, value) -> map.put((String) key, (String) value));
    } catch (Exception e) {
      log.error(e.toString());
    }

    return map;
  }
}
