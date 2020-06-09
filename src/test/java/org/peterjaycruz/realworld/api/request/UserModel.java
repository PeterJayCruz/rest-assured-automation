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
    this.user = new HashMap<>();
  }

  public User(String username, String email, String password) {
    this.user = new HashMap<>();
    createUserMap(username, email, password);
  }

  public HashMap<String, String> getUser() {
    return user;
  }

  public User createUserWithDefaultValues() {
    String username = StringUtilities.createUniqueString("username_");
    String email = username.concat("@email.com");
    String password = "testPassword";

    this.user = new HashMap<>();
    createUserMap(username, email, password);

    return this;
  }

  public User username(String username) {
    this.user.put(USERNAME, username);
    return this;
  }

  public User email(String email) {
    this.user.put(EMAIL, email);
    return this;
  }

  public User password(String password) {
    this.user.put(PASSWORD, password);
    return this;
  }

  private void createUserMap(String username, String email, String password) {

    this.user.put(USERNAME, username);
    this.user.put(EMAIL, email);
    this.user.put(PASSWORD, password);
  }
}

// package org.peterjaycruz.realworld.api.request;
//
// import org.peterjaycruz.realworld.utilities.StringUtilities;
//
// import java.util.HashMap;
//
// public class User {
//
//   private String username;
//   private String email;
//   private String password;
//
//   public User() {
//     username = StringUtilities.createUniqueString("username_");
//     email = username.concat("@email.com");
//     password = "testPassword";
//   }
//
//   public User(String username, String email, String password) {
//     this.username = username;
//     this.email = email;
//     this.password = password;
//   }
//
//   public User username(String username) {
//     this.username = username;
//     return this;
//   }
//
//   public User email(String email) {
//     this.email = email;
//     return this;
//   }
//
//   public User password(String password) {
//     this.password = password;
//     return this;
//   }
//
//   public HashMap<String, String> getUser() {
//     return new HashMap<>();
//   }
// }
