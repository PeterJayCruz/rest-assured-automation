package org.peterjaycruz.realworld.api.test;

import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.api.request.RequestBody;
import org.peterjaycruz.realworld.api.response.ApiResponseBody;
import org.peterjaycruz.realworld.api.response.ExpectedResponse;
import org.peterjaycruz.realworld.api.utilities.RestAssuredUtilities;

public class ApiTest<T extends RequestBody, V extends ApiResponseBody> {

  public String testName;
  public RequestSpecification requestSpec;
  public String endpoint;
  public T requestBody;
  public ExpectedResponse<V> expectedResponse;

  public ApiTest() {
    requestSpec = RestAssuredUtilities.createRequestSpecification();
  }

  public ApiTest<T, V> setTestName(String testName) {
    this.testName = testName;
    return this;
  }

  public ApiTest<T, V> setEndpoint(String basePath) {
    this.endpoint = basePath;
    return this;
  }

  public ApiTest<T, V> setRequestBody(T requestBody) {
    this.requestBody = requestBody;
    requestSpec.body(requestBody);
    return this;
  }

  public ApiTest<T, V> setExpectedResponse(ExpectedResponse<V> expectedResponse) {
    this.expectedResponse = expectedResponse;
    return this;
  }
}
