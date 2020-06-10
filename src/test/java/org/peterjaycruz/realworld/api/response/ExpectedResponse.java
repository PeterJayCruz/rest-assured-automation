package org.peterjaycruz.realworld.api.response;

public class ExpectedResponse {

  private int statusCode;

  public int getStatusCode() {
    return statusCode;
  }

  public ExpectedResponse setStatusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }
}
