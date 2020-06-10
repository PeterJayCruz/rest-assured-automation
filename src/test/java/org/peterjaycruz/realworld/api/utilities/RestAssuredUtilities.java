package org.peterjaycruz.realworld.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.constants.Path;

public class RestAssuredUtilities {

  public static RequestSpecification createRequestSpecification(String basePath, Object body) {
    return new RequestSpecBuilder()
                .setBaseUri(Path.BASE_URI)
                .setBasePath(basePath)
                .addHeader("Content-Type", ContentType.JSON.toString())
                .setBody(body)
                .build();
      }
}
