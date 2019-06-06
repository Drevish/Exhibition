package com.drevish.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Views {
  private static Map<String, String> views = PropertyHandler.getProperties("jsp.properties");

  private Views() {
  }

  public static String getValue(String key) {
    return views.get(key);
  }
}
