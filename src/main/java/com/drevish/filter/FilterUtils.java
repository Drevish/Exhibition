package com.drevish.filter;

import com.drevish.model.entity.User;

import javax.servlet.http.HttpSession;

public final class FilterUtils {
  private FilterUtils() {
  }

  public static boolean userIsAuthorized(HttpSession session) {
    return session != null &&
            session.getAttribute("user") != null &&
            !((User) session.getAttribute("user"))
                    .getRole().equals(User.Role.UNKNOWN);
  }
}
