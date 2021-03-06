package org.peterjaycruz.realworld.api.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.api.constants.Path;
import org.peterjaycruz.realworld.api.request.RequestBody;

import static io.restassured.RestAssured.given;

public class RestAssuredUtilities {

  public static RequestSpecification createRequestSpecification(String basePath, RequestBody body) {
    return new RequestSpecBuilder()
                .setBaseUri(Path.BASE_URI)
                .setBasePath(basePath)
                .addHeader("Content-Type", ContentType.JSON.toString())
                .setBody(body)
                .build();
  }

  public static RequestSpecification createRequestSpecification() {
    return new RequestSpecBuilder()
                .setBaseUri(Path.BASE_URI)
                .addHeader("Content-Type", ContentType.JSON.toString())
                .build();
  }

  public static RequestSpecification addHeader(RequestSpecification requestSpec, String key, String value) {
    return requestSpec.header(key, value);
  }

  public static Response getRequest(RequestSpecification requestSpec, String endpoint) {
    return given()
              .spec(requestSpec)
           .when()
              .get(endpoint)
           .then()
              .extract()
              .response();
  }

  public static Response postRequest(RequestSpecification requestSpec, String endpoint) {
    return given()
              .spec(requestSpec)
           .when()
              .post(endpoint)
           .then()
              .extract()
              .response();
  }

  public static Response putRequest(RequestSpecification requestSpec, String endpoint) {
    return given()
              .spec(requestSpec)
           .when()
              .put(endpoint)
           .then()
              .extract()
              .response();
  }

  public static Response deleteRequest(RequestSpecification requestSpec, String endpoint) {
    return given()
              .spec(requestSpec)
           .when()
              .delete(endpoint)
           .then()
              .extract()
              .response();
  }

}
