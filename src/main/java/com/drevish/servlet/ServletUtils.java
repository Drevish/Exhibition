package com.drevish.servlet;

import javax.servlet.http.HttpServletRequest;

public final class ServletUtils {
  static public void setRequestErrorAttribute(ErrorAttribute attribute, HttpServletRequest req) {
    req.setAttribute(attribute.getName(), attribute.getValue());
  }
}
