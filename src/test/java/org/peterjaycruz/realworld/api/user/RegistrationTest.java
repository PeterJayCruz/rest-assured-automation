package org.peterjaycruz.realworld.api.user;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.api.request.UserModel;
import org.peterjaycruz.realworld.constants.Path;
import org.peterjaycruz.realworld.utilities.RestAssuredUtilities;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTest {

  @Test
  public void registerNewUser() {

    UserModel user = new UserModel().createUserWithDefaultValues();
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
    UserModel user = new UserModel("", "email@email.com", "testPassword");
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
    UserModel user = new UserModel("username", "", "testPassword");
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
    UserModel user = new UserModel("username", "email@email.com", "");
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
    UserModel uniqueUser = new UserModel().createUserWithDefaultValues();
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

    UserModel userWithDuplicateUsername = new UserModel(uniqueUser.getUser().get("username"), "uniqueEmail@email.com", "testPassword");
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
    UserModel uniqueUser = new UserModel().createUserWithDefaultValues();
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

    UserModel userWithDuplicateUsername = new UserModel("uniqueUsername", uniqueUser.getUser().get("email"), "testPassword");
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
