package org.peterjaycruz.realworld.api.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorResponseBody extends ApiResponseBody {

  private HashMap<String, ArrayList<String>> errors;

  public ErrorResponseBody() {
    this.errors = new HashMap<>();
  }

  public HashMap<String, ArrayList<String>> getErrors() {
    return errors;
  }

  public ErrorResponseBody addErrorMessage(String errorField, String message) {
    if(!errors.containsKey(errorField)) {
      errors.put(errorField, new ArrayList<String>());
    }

    errors.get(errorField).add(message);
    return this;
  }
}
