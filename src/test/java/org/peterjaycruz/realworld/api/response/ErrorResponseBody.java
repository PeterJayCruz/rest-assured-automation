package org.peterjaycruz.realworld.api.response;

import java.util.HashMap;

public class ErrorResponseBody extends ApiResponseBody {

  public HashMap<String, String[]> errors;

  public ErrorResponseBody() {
    this.errors = new HashMap<>();
  }
}
