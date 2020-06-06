package org.peterjaycruz.realworld.api.user;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.api.request.User;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTest {

  private static final String USERS_ENDPOINT = "http://localhost:8080/users";

  private static final String BASE_URI = "http://localhost:8080";
  private static final String REGISTRATION_BASE_PATH = "/users";

  @Test
  public void registerNewUser() {

    User user = new User();

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(createRequestSpecification(user))
                        .when()
                          .post(USERS_ENDPOINT)
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
    User user = new User("", "email@email.com", "testPassword");

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(createRequestSpecification(user))
                        .when()
                          .post(USERS_ENDPOINT)
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
    User user = new User("username", "", "testPassword");

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(createRequestSpecification(user))
                        .when()
                          .post(USERS_ENDPOINT)
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
    User user = new User("username", "email@email.com", "");

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(createRequestSpecification(user))
                        .when()
                          .post(USERS_ENDPOINT)
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
    User uniqueUser = new User();
    given()
      .log().uri()
      .log().body()
      .spec(createRequestSpecification(uniqueUser))
    .when()
      .post(USERS_ENDPOINT)
    .then()
      .log().status()
      .log().body(true);

    User userWithDuplicateUsername = new User(uniqueUser.getUser().get("username"), "uniqueEmail@email.com", "testPassword");

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(createRequestSpecification(userWithDuplicateUsername))
                        .when()
                          .post(USERS_ENDPOINT)
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
    User uniqueUser = new User();
    given()
      .log().uri()
      .log().body()
      .spec(createRequestSpecification(uniqueUser))
    .when()
      .post(USERS_ENDPOINT)
    .then()
      .log().status()
      .log().body(true);

    User userWithDuplicateUsername = new User("uniqueUsername", uniqueUser.getUser().get("email"), "testPassword");

    Response response = given()
                          .log().uri()
                          .log().body()
                          .spec(createRequestSpecification(userWithDuplicateUsername))
                        .when()
                          .post(USERS_ENDPOINT)
                        .then()
                          .log().status()
                          .log().body(true)
                          .statusCode(422)
                          .body("errors.email[0]", equalTo("duplicated email"))
                          .extract()
                          .response();
  }

  private static RequestSpecification createRequestSpecification(User user) {
    return new RequestSpecBuilder()
                  .setBaseUri(BASE_URI)
                  .setBasePath(REGISTRATION_BASE_PATH)
                  .addHeader("Content-Type", ContentType.JSON.toString())
                  .setBody(user)
                  .build();
  }
}
