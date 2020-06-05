package org.peterjaycruz.realworld.api.request;

import org.peterjaycruz.realworld.utilities.StringUtilities;

import java.util.HashMap;

public class User {

  // request field names
  private static final String USERNAME = "username";
  private static final String EMAIL = "email";
  private static final String PASSWORD = "password";

  private HashMap<String, String> user;

  public User() {
    String username = StringUtilities.createUniqueString("username_");
    String email = username.concat("@email.com");
    String password = "testPassword";

    this.user = new HashMap<>();
    createUserMap(username, email, password);
  }

  public User(String username, String email, String password) {
    this.user = new HashMap<>();
    createUserMap(username, email, password);
  }

  public HashMap<String, String> getUser() {
    return user;
  }

  public User setUser(HashMap<String, String> user) {
    this.user = user;
    return this;
  }

  private void createUserMap(String username, String email, String password) {

    this.user.put(USERNAME, username);
    this.user.put(EMAIL, email);
    this.user.put(PASSWORD, password);
  }
}
