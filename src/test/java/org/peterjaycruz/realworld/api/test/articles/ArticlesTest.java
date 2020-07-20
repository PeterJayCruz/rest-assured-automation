package org.peterjaycruz.realworld.api.test.articles;

import io.restassured.response.Response;
import org.peterjaycruz.realworld.api.constants.Path;
import org.peterjaycruz.realworld.api.request.ArticlesRequestBody;
import org.peterjaycruz.realworld.api.response.ArticlesResponseBody;
import org.peterjaycruz.realworld.api.response.ExpectedResponse;
import org.peterjaycruz.realworld.api.response.ResponseArticle;
import org.peterjaycruz.realworld.api.response.UserResponseBody;
import org.peterjaycruz.realworld.api.test.ApiTest;
import org.peterjaycruz.realworld.api.utilities.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ArticlesTest {

  String testUsername;
  String testEmail;
  String testPassword;
  UserResponseBody user;
  String tokenValue;

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

    PrintUtilities.printToConsole("test user was successfully created");
    PrintUtilities.printDivider();
  }

  @Test
  public void testCreateArticleWithTagList() {
    PrintUtilities.printDivider();

    // create test request
    String title = StringUtilities.createUniqueString("article_title");
    String description = StringUtilities.createUniqueString("description");
    String body = StringUtilities.createUniqueString("test article body");

    int numTags = 3;
    ArrayList<String> tagList = new ArrayList<>();
    for(int i = 0; i < numTags; i++) {
      tagList.add(StringUtilities.createUniqueString("tag_" + i));
    }

    ArticlesRequestBody articlesRequestBody = new ArticlesRequestBody()
            .title(title)
            .description(description)
            .body(body)
            .tagList(tagList);

    // create test expected response
    ArticlesResponseBody expectedArticlesResponseBody = new ArticlesResponseBody()
            .title(title)
            .description(description)
            .body(body)
            .tagList(tagList);

    ExpectedResponse<ArticlesResponseBody> expectedResponse = new ExpectedResponse<ArticlesResponseBody>()
            .setExpectedStatusCode(200);

    // create test object
    String tokenHeaderValue = "Token " + tokenValue;
    ApiTest<ArticlesRequestBody, ArticlesResponseBody> test = new ApiTest<ArticlesRequestBody, ArticlesResponseBody>()
            .setTestName("create new article (with list of tags)")
            .setEndpoint(Path.ARTICLES)
            .setRequestBody(articlesRequestBody)
            .addHeader("Authorization", tokenHeaderValue)
            .setExpectedResponse(expectedResponse);

    System.out.println("Test name: " + test.testName);

    // send request and receive response
    Response actualResponse = RestAssuredUtilities.postRequest(test.requestSpec, test. endpoint);

    // validate response
    int failCount = 0;
    failCount += ValidationUtilities.validateStatusCode(actualResponse.statusCode(), test.expectedResponse.getExpectedStatusCode());
    failCount += validateSuccessfulArticlesResponse(actualResponse, expectedArticlesResponseBody);

    Assert.assertEquals(failCount, 0);
    if(failCount == 0) {
      System.out.println("Test Result: pass");
    }

    PrintUtilities.printDivider();
  }

  @Test
  public void testCreateArticleWithEmptyTagList() {
    PrintUtilities.printDivider();

    // create test request
    String title = StringUtilities.createUniqueString("article_title");
    String description = StringUtilities.createUniqueString("description");
    String body = StringUtilities.createUniqueString("test article body");

    ArticlesRequestBody articlesRequestBody = new ArticlesRequestBody()
            .title(title)
            .description(description)
            .body(body);

    // create test expected response
    ArticlesResponseBody expectedArticlesResponseBody = new ArticlesResponseBody()
            .title(title)
            .description(description)
            .body(body);

    ExpectedResponse<ArticlesResponseBody> expectedResponse = new ExpectedResponse<ArticlesResponseBody>()
            .setExpectedStatusCode(200);

    // create test object
    String tokenHeaderValue = new StringBuilder("Token ").append(tokenValue).toString();

    ApiTest<ArticlesRequestBody, ArticlesResponseBody> test = new ApiTest<ArticlesRequestBody, ArticlesResponseBody>()
            .setTestName("create new article (empty list of tags)")
            .setEndpoint(Path.ARTICLES)
            .setRequestBody(articlesRequestBody)
            .addHeader("Authorization", tokenHeaderValue)
            .setExpectedResponse(expectedResponse);

    System.out.println("Test name: " + test.testName);

    // send request and receive response
    Response actualResponse = RestAssuredUtilities.postRequest(test.requestSpec, test. endpoint);

    // validate response
    int failCount = 0;
    failCount += ValidationUtilities.validateStatusCode(actualResponse.statusCode(), test.expectedResponse.getExpectedStatusCode());
    failCount += validateSuccessfulArticlesResponse(actualResponse, expectedArticlesResponseBody);

    Assert.assertEquals(failCount, 0);
    if(failCount == 0) {
      System.out.println("Test Result: pass");
    }

    PrintUtilities.printDivider();
  }

  private static int validateSuccessfulArticlesResponse(Response actualResponse, ArticlesResponseBody expectedResponse) {
    int failCount = 0;

    ResponseArticle actualResponseRoot = actualResponse.body().as(ArticlesResponseBody.class).getArticle();
    ResponseArticle expectedResponseRoot = expectedResponse.getArticle();

    // verify title
    failCount += validateField("article.title", actualResponseRoot.getTitle(), expectedResponseRoot.getTitle());

    // verify description
    failCount+= validateField("article.description", actualResponseRoot.getDescription(), expectedResponseRoot.getDescription());

    // verify body
    failCount+= validateField("article.body", actualResponseRoot.getBody(), expectedResponseRoot.getBody());

    // verify tagList only if a list of tags is provided
    if(expectedResponseRoot.getTagList().size() > 0) {
      failCount+= validateList("article.tagList", actualResponseRoot.getTagList(), expectedResponseRoot.getTagList());
    }

    return failCount;
  }

  private static int validateField(String field, String actual, String expected) {
    int failCount = 0;

    if(!actual.equals(expected)) {
      failCount++;
      System.out.print("FAILED FIELD VALIDATION: " + field + ": ");
      PrintUtilities.printExpectedVsActual(expected, actual);
    }

    return failCount;
  }

  private static int validateList(String list, ArrayList<String> actualTagList, ArrayList<String> expectedTagList) {
    int failCount = 0;

    // verify sizes are equal
    if(actualTagList.size() != expectedTagList.size()) {
      failCount++;
      System.out.println("FAILURE: actual tagList size is not equal to expected tagList size");
      // TODO: print expected vs actual
    }

    for(String expectedTag : expectedTagList) {
      if(!actualTagList.contains(expectedTag)) {
        System.out.println("FAILURE: actual tag list is missing tag: " + expectedTag);
        failCount++;
      }
    }

    return failCount;
  }
}
