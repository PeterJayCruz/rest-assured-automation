package org.peterjaycruz.realworld.api.response;

import java.util.HashMap;

public class UserResponseBody extends ApiResponseBody {

  public HashMap<String, String> user;

  public UserResponseBody() {
    this.user = new HashMap<>();
  }

  public HashMap<String, String> getRoot() {
    return user;
  }
}