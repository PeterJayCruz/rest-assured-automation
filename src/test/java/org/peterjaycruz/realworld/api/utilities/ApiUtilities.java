package org.peterjaycruz.realworld.api.utilities;

import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.api.constants.Path;
import org.peterjaycruz.realworld.api.request.UserRequestBody;

public class ApiUtilities {

  public static UserRequestBody createTestUser() {
    UserRequestBody user = new UserRequestBody().createUserBodyWithDefaultValues();
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION, user);

    RestAssuredUtilities.postRequest(requestSpec, "");

    return user;
  }
}
