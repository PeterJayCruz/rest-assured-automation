package org.peterjaycruz.realworld.api.test.user;

import io.restassured.response.Response;
import org.peterjaycruz.realworld.api.constants.Path;
import org.peterjaycruz.realworld.api.request.UserRequestBody;
import org.peterjaycruz.realworld.api.response.ErrorResponseBody;
import org.peterjaycruz.realworld.api.response.ExpectedResponse;
import org.peterjaycruz.realworld.api.response.UserResponseBody;
import org.peterjaycruz.realworld.api.test.ApiTest;
import org.peterjaycruz.realworld.api.utilities.ApiUtilities;
import org.peterjaycruz.realworld.api.utilities.RestAssuredUtilities;
import org.peterjaycruz.realworld.api.utilities.StringUtilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationTest {

  String testUsername;
  String testEmail;
  String testPassword;
  UserResponseBody existingUser;

  @BeforeClass
  public void createTestUser() {
    // precondition: create existing user
    testUsername = StringUtilities.createUniqueString("username_");
    testEmail = testUsername.concat("@email.com");
    testPassword = "validPassword";

    existingUser = ApiUtilities.createTestUser(testUsername, testEmail, testPassword);
  }

  @Test
  public void testSuccessfulAuthentication() {
    printDivider();
    // create test request
    UserRequestBody loginUserRequestBody = new UserRequestBody()
            .email(testEmail)
            .password("validPassword");

    // create test expected response
    UserResponseBody expectedUserResponseBody = new UserResponseBody();
    expectedUserResponseBody.user.put("email", testEmail);
    expectedUserResponseBody.user.put("username", testUsername);

    ExpectedResponse<UserResponseBody> expectedResponse = new ExpectedResponse<UserResponseBody>()
            .setExpectedStatusCode(200);

    // create test object
    ApiTest<UserRequestBody, UserResponseBody> test = new ApiTest<UserRequestBody, UserResponseBody>()
            .setTestName("sucessful authentication")
            .setEndpoint(Path.LOGIN)
            .setRequestBody(loginUserRequestBody)
            .setExpectedResponse(expectedResponse);

    System.out.println("Test name: " + test.testName);

    // send request and receive response
    Response actualResponse = RestAssuredUtilities.postRequest(test.requestSpec, test.endpoint);

    // validate response
    int failCount = 0;
    failCount += validateStatusCode(actualResponse.getStatusCode(), test.expectedResponse.getExpectedStatusCode());
    failCount += validateSuccessResponseBody(actualResponse, expectedUserResponseBody);

    Assert.assertEquals(failCount, 0);
    if(failCount == 0) {
      System.out.println("Test Result: pass");
    }
    printDivider();
  }

  @Test
  public void testFailedAuthenticationInvalidPassword() {
    printDivider();
    // create test request
    UserRequestBody loginUserRequestBody = new UserRequestBody()
            .email(testEmail)
            .password("invalidPassword");

    // create test expected response
    ErrorResponseBody errorResponseBody = new ErrorResponseBody()
            .addErrorMessage("password", "invalid email or pasfsword");

    ExpectedResponse<ErrorResponseBody> expectedResponse = new ExpectedResponse<ErrorResponseBody>()
            .setExpectedStatusCode(422);

    // create test object
    ApiTest<UserRequestBody, ErrorResponseBody> test = new ApiTest<UserRequestBody, ErrorResponseBody>()
            .setTestName("failed authentication")
            .setEndpoint(Path.LOGIN)
            .setRequestBody(loginUserRequestBody)
            .setExpectedResponse(expectedResponse);

    System.out.println("Test name: " + test.testName);

    // send request and receive response
    Response actualResponse = RestAssuredUtilities.postRequest(test.requestSpec, test.endpoint);

    // validate response
    int failCount = 0;
    failCount += validateStatusCode(actualResponse.getStatusCode(), test.expectedResponse.getExpectedStatusCode());
    failCount += validateErrorResponseBody(actualResponse, errorResponseBody);

    Assert.assertEquals(failCount, 0);
    if(failCount == 0) {
      System.out.println("Test Result: pass");
    }
    printDivider();
  }

  private static int validateStatusCode(int actualStatusCode, int expectedStatusCode) {
    if(actualStatusCode == expectedStatusCode) {
      return 0;
    }

    return 1;
  }

  private static int validateSuccessResponseBody(Response actualResponse, UserResponseBody expectedResponse) {
    int failCount = 0;

    HashMap<String, String> actualResponseBodyValues = actualResponse.body().as(UserResponseBody.class).user;

    for(Map.Entry<String, String> entry : expectedResponse.user.entrySet()) {
      String actual = actualResponseBodyValues.get(entry.getKey());
      String expected = entry.getValue();

      if(!actual.equals(expected)) {
        System.out.println("FAILED FIELD VALIDATION: " + entry.getKey());
        printExpectedVsActual(expected, actual);
       failCount++;
      }
    }

    return failCount;
  }

  private static int validateErrorResponseBody(Response actualResponse, ErrorResponseBody expectedResponse) {
    int failCount = 0;

    HashMap<String, ArrayList<String>> actualResponseBodyValues = actualResponse.body().as(ErrorResponseBody.class).getErrors();

    for(Map.Entry<String, ArrayList<String>> entry : expectedResponse.getErrors().entrySet()) {
      ArrayList<String> actual = actualResponseBodyValues.get(entry.getKey());
      ArrayList<String> expected = entry.getValue();

      for(int i = 0; i < expected.size(); i++) {
        if(!actual.get(i).equals(expected.get(i))) {
          System.out.println("FAILED ERROR MESSAGE VALIDATION: " + entry.getKey());
          printExpectedVsActual(expected.get(i), actual.get(i));
          failCount++;
        }
      }
    }

    return failCount;
  }

  private static void printExpectedVsActual(String expected, String actual) {
    System.out.println("expected: " + expected);
    System.out.println("actual: " + actual);
  }

  private static void printDivider() {
    System.out.println("==================================================");
  }
}
