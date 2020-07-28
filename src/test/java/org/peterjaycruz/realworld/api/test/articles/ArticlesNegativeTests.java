package org.peterjaycruz.realworld.api.test.articles;

import io.restassured.response.Response;
import org.peterjaycruz.realworld.api.constants.Path;
import org.peterjaycruz.realworld.api.request.ArticlesRequestBody;
import org.peterjaycruz.realworld.api.response.ErrorResponseBody;
import org.peterjaycruz.realworld.api.response.ExpectedResponse;
import org.peterjaycruz.realworld.api.response.UserResponseBody;
import org.peterjaycruz.realworld.api.test.ApiTest;
import org.peterjaycruz.realworld.api.utilities.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ArticlesNegativeTests {

  String testUsername;
  String testEmail;
  String testPassword;
  UserResponseBody user;
  String tokenValue;
  String authorizationHeaderValue;

  @BeforeClass
  public void createTestUser() {
    PrintUtilities.printDivider();
    PrintUtilities.printToConsole("creating test user now");

    // precondition: create existing user and log in
    testUsername = StringUtilities.createUniqueString("username");
    testEmail = testUsername.concat("@email.com");
    testPassword = "validPassword";

    user = ApiUtilities.createTestUser(testUsername, testEmail, testPassword);
    tokenValue = user.getRoot().get("token");
    user = ApiUtilities.logInAsUser(testEmail, testPassword, tokenValue);
    authorizationHeaderValue = new StringBuilder("Token ").append(tokenValue).toString();

    PrintUtilities.printToConsole("test user was successfully created");
    PrintUtilities.printDivider();
  }

  @Test()
  public void testTitleIsRequired_Empty() {
    PrintUtilities.printDivider();

    // create test request
    String title = "";
    String description = StringUtilities.createUniqueString("description");
    String body = StringUtilities.createUniqueString("test article body");

    ArticlesRequestBody articlesRequestBody = new ArticlesRequestBody()
            .title(title)
            .description(description)
            .body(body);

    // create test expected response
    ErrorResponseBody errorResponseBody = new ErrorResponseBody()
            .addErrorMessage("title", "can't be empty");

    ExpectedResponse<ErrorResponseBody> expectedResponse = new ExpectedResponse<ErrorResponseBody>()
            .setExpectedStatusCode(422);

    // create test object
    ApiTest<ArticlesRequestBody, ErrorResponseBody> test = new ApiTest<ArticlesRequestBody, ErrorResponseBody>()
            .setTestName("require title (empty)")
            .setEndpoint(Path.ARTICLES)
            .addHeader("Authorization", authorizationHeaderValue)
            .setRequestBody(articlesRequestBody)
            .setExpectedResponse(expectedResponse);

    // send and receive request
    PrintUtilities.printToConsole("Test name: " + test.testName );
    Response actualResponse = RestAssuredUtilities.postRequest(test.requestSpec, test.endpoint);

    // validate response
    int failCount = 0;
    failCount += ValidationUtilities.validateStatusCode(actualResponse.getStatusCode(), test.expectedResponse.getExpectedStatusCode());
    failCount += ValidationUtilities.validateErrorResponseBody(actualResponse, errorResponseBody);

    // analyze and print test results
    ValidationUtilities.analyzeTestCaseResults(failCount);
    PrintUtilities.printDivider();
  }

  @Test
  public void testTitleIsRequired_Null() {
    PrintUtilities.printDivider();

    // create test request
    String description = StringUtilities.createUniqueString("description");
    String body = StringUtilities.createUniqueString("test article body");

    ArticlesRequestBody articlesRequestBody = new ArticlesRequestBody()
            .title(null)
            .description(description)
            .body(body);

    // create test expected response
    ErrorResponseBody errorResponseBody = new ErrorResponseBody()
            .addErrorMessage("title", "can't be empty");

    ExpectedResponse<ErrorResponseBody> expectedResponse = new ExpectedResponse<ErrorResponseBody>()
            .setExpectedStatusCode(422);

    // create test object
    ApiTest<ArticlesRequestBody, ErrorResponseBody> test = new ApiTest<ArticlesRequestBody, ErrorResponseBody>()
            .setTestName("require title (null)")
            .setEndpoint(Path.ARTICLES)
            .addHeader("Authorization", authorizationHeaderValue)
            .setRequestBody(articlesRequestBody)
            .setExpectedResponse(expectedResponse);

    // send and receive request
    PrintUtilities.printToConsole("Test name: " + test.testName );
    Response actualResponse = RestAssuredUtilities.postRequest(test.requestSpec, test.endpoint);

    // validate response
    int failCount = 0;
    failCount += ValidationUtilities.validateStatusCode(actualResponse.getStatusCode(), test.expectedResponse.getExpectedStatusCode());
    failCount += ValidationUtilities.validateErrorResponseBody(actualResponse, errorResponseBody);

    // analyze and print test results
    ValidationUtilities.analyzeTestCaseResults(failCount);
    PrintUtilities.printDivider();
  }
}
