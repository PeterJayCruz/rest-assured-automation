package org.peterjaycruz.realworld.api.response;

public class ExpectedResponse {

  private int expectedStatusCode;
  private ApiResponseBody expectedResponseBody;

  public int getExpectedStatusCode() {
    return expectedStatusCode;
  }

  public ExpectedResponse setExpectedStatusCode(int expectedStatusCode) {
    this.expectedStatusCode = expectedStatusCode;
    return this;
  }

  public ApiResponseBody getExpectedResponseBody() {
    return expectedResponseBody;
  }

  public ExpectedResponse setExpectedResponseBody(ApiResponseBody expectedResponseBody) {
    this.expectedResponseBody = expectedResponseBody;
    return this;
  }
}
