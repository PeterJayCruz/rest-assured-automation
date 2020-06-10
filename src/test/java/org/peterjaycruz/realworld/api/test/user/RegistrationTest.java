package org.peterjaycruz.realworld.api.test.user;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.api.request.UserRequestBody;
import org.peterjaycruz.realworld.api.constants.Path;
import org.peterjaycruz.realworld.api.utilities.RestAssuredUtilities;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTest {

  @Test
  public void registerNewUser() {

    UserRequestBody user = new UserRequestBody().createUserBodyWithDefaultValues();
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION_BASE_PATH, user);

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(requestSpec)
                        .when()
                          .post()
                        .then()
                          .log().status()
                          .log().body(true)
                          .statusCode(201)
                          .body("user.username", equalTo(user.getUser().get("username")))
                          .body("user.email", equalTo(user.getUser().get("email")))
                          .extract()
                          .response();

  }

  @Test
  public void requireUsername() {
    UserRequestBody user = new UserRequestBody("", "email@email.com", "testPassword");
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION_BASE_PATH, user);

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(requestSpec)
                        .when()
                          .post()
                        .then()
                          .log().status()
                          .log().body(true)
                          .statusCode(422)
                          .body("errors.username[0]", equalTo("can't be empty"))
                          .extract()
                          .response();
  }

  @Test
  public void requireEmail() {
    UserRequestBody user = new UserRequestBody("username", "", "testPassword");
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION_BASE_PATH, user);

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(requestSpec)
                        .when()
                          .post()
                        .then()
                          .log().status()
                          .log().body(true)
                          .statusCode(422)
                          .body("errors.email[0]", equalTo("can't be empty"))
                          .extract()
                          .response();
  }

  @Test
  public void requirePassword() {
    UserRequestBody user = new UserRequestBody("username", "email@email.com", "");
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION_BASE_PATH, user);

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(requestSpec)
                        .when()
                          .post()
                        .then()
                          .log().status()
                          .log().body(true)
                          .statusCode(422)
                          .body("errors.password[0]", equalTo("can't be empty"))
                          .extract()
                          .response();
  }

  @Test
  public void requireUniqueUsername() {
    UserRequestBody uniqueUser = new UserRequestBody().createUserBodyWithDefaultValues();
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION_BASE_PATH, uniqueUser);

    given()
      .log().uri()
      .log().body()
      .spec(requestSpec)
    .when()
      .post()
    .then()
      .log().status()
      .log().body(true);

    UserRequestBody userWithDuplicateUsername = new UserRequestBody(uniqueUser.getUser().get("username"), "uniqueEmail@email.com", "testPassword");
    RequestSpecification requestSpec2 = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION_BASE_PATH, userWithDuplicateUsername);

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(requestSpec2)
                        .when()
                          .post()
                        .then()
                          .log().status()
                          .log().body(true)
                          .statusCode(422)
                          .body("errors.username[0]", equalTo("duplicated username"))
                          .extract()
                          .response();
  }

  @Test
  public void requireUniqueEmail() {
    UserRequestBody uniqueUser = new UserRequestBody().createUserBodyWithDefaultValues();
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION_BASE_PATH, uniqueUser);

    given()
      .log().uri()
      .log().body()
      .spec(requestSpec)
    .when()
      .post()
    .then()
      .log().status()
      .log().body(true);

    UserRequestBody userWithDuplicateUsername = new UserRequestBody("uniqueUsername", uniqueUser.getUser().get("email"), "testPassword");
    RequestSpecification requestSpec2 = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION_BASE_PATH, userWithDuplicateUsername);

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(requestSpec2)
                        .when()
                          .post()
                        .then()
                          .log().status()
                          .log().body(true)
                          .statusCode(422)
                          .body("errors.email[0]", equalTo("duplicated email"))
                          .extract()
                          .response();
  }
}
