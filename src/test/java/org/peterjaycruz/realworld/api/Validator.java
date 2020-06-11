package org.peterjaycruz.realworld.api;

import io.restassured.response.Response;
import org.peterjaycruz.realworld.api.response.ExpectedResponse;
import org.peterjaycruz.realworld.api.response.UserResponseBody;

import java.util.HashMap;
import java.util.Map;

public class Validator {

  private Response actualResponse;
  private ExpectedResponse expectedResponse;
  public int failCount;

  public Validator(Response actualResponse, ExpectedResponse expectedResponse) {
    this.actualResponse = actualResponse;
    this.expectedResponse = expectedResponse;
    failCount = 0;
  }

  public Validator validateStatusCode() {
    if(actualResponse.getStatusCode() != expectedResponse.getExpectedStatusCode()) {
      System.out.println("Status code test failed");
      System.out.println("expected: " + expectedResponse.getExpectedStatusCode());
      System.out.println("actual: " + actualResponse.getStatusCode());
      failCount++;
    }

    return this;
  }

  public Validator validateResponseBody() {

    HashMap<String, String> actualResponseBodyFields = actualResponse.body().as(UserResponseBody.class).user;
    HashMap<String, String> expectedResponseBodyFields = expectedResponse.getExpectedResponseBody().getRoot();

    if(actualResponseBodyFields.size() != expectedResponseBodyFields.size()) {
      System.out.print("Response body size are not equal: ");
      System.out.print("expected: " + expectedResponseBodyFields.size() + " | ");
      System.out.println("actual: " + actualResponseBodyFields.size());
      failCount++;
    }

    for(Map.Entry<String, String> entry : actualResponseBodyFields.entrySet()) {
      String key = entry.getKey();
      String actualValue = entry.getValue();

      if(!actualValue.equals(expectedResponseBodyFields.get(key))) {
        System.out.print("FAILED: ");
        System.out.print("expected: " + expectedResponseBodyFields.get(key) + " | ");
        System.out.println("actual: " + actualValue);
        failCount++;
      }
    }

    return this;
  }

  public Validator printFailCount() {
    System.out.println("number of failures: " + failCount);
    return this;
  }
}
