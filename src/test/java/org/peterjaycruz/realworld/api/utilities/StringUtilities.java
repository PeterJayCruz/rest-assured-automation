package org.peterjaycruz.realworld.api.utilities;

import java.util.Date;

public class StringUtilities {

  public static String createUniqueString(String prefix) {
    return new StringBuilder(prefix)
                .append(new Date()
                .toInstant()
                .toEpochMilli())
                .toString();
  }

}
