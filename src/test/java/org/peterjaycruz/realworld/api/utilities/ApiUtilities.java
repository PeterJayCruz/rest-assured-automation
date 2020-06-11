package org.peterjaycruz.realworld.api.utilities;

import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.api.constants.Path;
import org.peterjaycruz.realworld.api.request.UserRequestBody;
import org.peterjaycruz.realworld.api.response.UserResponseBody;

public class ApiUtilities {

  public static UserRequestBody createTestUser() {
    UserRequestBody user = new UserRequestBody().createUserBodyWithDefaultValues();
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION, user);

    RestAssuredUtilities.postRequest(requestSpec, "");

    return user;
  }

  public static UserResponseBody createTestUser(UserRequestBody user) {
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION, user);
    
    return RestAssuredUtilities.postRequest(requestSpec, "")
                                  .body()
                                  .as(UserResponseBody.class);
  }
}
