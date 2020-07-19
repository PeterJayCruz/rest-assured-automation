package org.peterjaycruz.realworld.api.utilities;

import io.restassured.specification.RequestSpecification;
import org.peterjaycruz.realworld.api.constants.Path;
import org.peterjaycruz.realworld.api.request.UserRequestBody;
import org.peterjaycruz.realworld.api.response.UserResponseBody;

public class ApiUtilities {

  public static UserResponseBody createTestUser(UserRequestBody user) {
    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.REGISTRATION, user);
    
    return RestAssuredUtilities.postRequest(requestSpec, "")
                                  .body()
                                  .as(UserResponseBody.class);
  }

  public static UserResponseBody createTestUser() {
    return createTestUser(new UserRequestBody().createUserBodyWithDefaultValues());
  }

  public static UserResponseBody createTestUser(String username, String email, String password) {
    return createTestUser(new UserRequestBody(username, email, password));
  }

  public static UserResponseBody logInAsUser(String email, String password, String token) {
    UserRequestBody user = new UserRequestBody().email(email).password(password);

    RequestSpecification requestSpec = RestAssuredUtilities.createRequestSpecification(Path.LOGIN, user);

    return RestAssuredUtilities.postRequest(requestSpec, "")
                                  .body()
                                  .as(UserResponseBody.class);
  }
}
