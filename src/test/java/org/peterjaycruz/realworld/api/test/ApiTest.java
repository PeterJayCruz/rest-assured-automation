package org.peterjaycruz.realworld.api.test;

import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.api.request.RequestBody;
import org.peterjaycruz.realworld.api.response.ExpectedResponse;
import org.peterjaycruz.realworld.api.utilities.RestAssuredUtilities;

public class ApiTest {

  public String testName;
  public RequestSpecification requestSpec;
  public String endpoint;
  public RequestBody requestBody;
  public ExpectedResponse expectedResponse;

  public ApiTest() {
    requestSpec = RestAssuredUtilities.createRequestSpecification();
  }

  public ApiTest setTestName(String testName) {
    this.testName = testName;
    return this;
  }

  public ApiTest setEndpoint(String basePath) {
    this.endpoint = basePath;
    return this;
  }

  public ApiTest setRequestBody(RequestBody requestBody) {
    this.requestBody = requestBody;
    requestSpec.body(requestBody);
    return this;
  }

  public ApiTest setExpectedResponse(ExpectedResponse expectedResponse) {
    this.expectedResponse = expectedResponse;
    return this;
  }
}
