package org.peterjaycruz.realworld.api.response;

public class ExpectedResponse<T extends ApiResponseBody> {

  private int expectedStatusCode;
  private T expectedResponseBody;

  public int getExpectedStatusCode() {
    return expectedStatusCode;
  }

  public ExpectedResponse<T> setExpectedStatusCode(int expectedStatusCode) {
    this.expectedStatusCode = expectedStatusCode;
    return this;
  }

  public T getExpectedResponseBody() {
    return expectedResponseBody;
  }

  public ExpectedResponse<T> setExpectedResponseBody(T expectedResponseBody) {
    this.expectedResponseBody = expectedResponseBody;
    return this;
  }
}
