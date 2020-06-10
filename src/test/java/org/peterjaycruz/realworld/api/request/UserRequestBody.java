package org.peterjaycruz.realworld.api.request;

import org.peterjaycruz.realworld.api.utilities.StringUtilities;

import java.util.HashMap;

public class UserRequestBody extends RequestBody {

  // request field names
  private static final String USERNAME = "username";
  private static final String EMAIL = "email";
  private static final String PASSWORD = "password";

  private HashMap<String, String> user;

  public UserRequestBody() {
    this.user = new HashMap<>();
  }

  public UserRequestBody(String username, String email, String password) {
    this.user = new HashMap<>();
    createUserMap(username, email, password);
  }

  public UserRequestBody createUserBodyWithDefaultValues() {
    String username = StringUtilities.createUniqueString("username_");
    String email = username.concat("@email.com");
    String password = "testPassword";

    this.user = new HashMap<>();
    createUserMap(username, email, password);

    return this;
  }

  public HashMap<String, String> getUser() {
    return user;
  }

  public UserRequestBody username(String username) {
    this.user.put(USERNAME, username);
    return this;
  }

  public UserRequestBody email(String email) {
    this.user.put(EMAIL, email);
    return this;
  }

  public UserRequestBody password(String password) {
    this.user.put(PASSWORD, password);
    return this;
  }

  private void createUserMap(String username, String email, String password) {

    this.user.put(USERNAME, username);
    this.user.put(EMAIL, email);
    this.user.put(PASSWORD, password);
  }
}