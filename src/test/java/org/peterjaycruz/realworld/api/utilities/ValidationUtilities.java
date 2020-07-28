package org.peterjaycruz.realworld.api.utilities;

import io.restassured.response.Response;
import org.peterjaycruz.realworld.api.response.ErrorResponseBody;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValidationUtilities {

  public static int validateStatusCode(int actualStatusCode, int expectedStatusCode) {
    if(actualStatusCode != expectedStatusCode) {
      PrintUtilities.printToConsole("FAILED STATUS CODE VALIDATION");
      System.out.print("\t");
      PrintUtilities.printExpectedVsActual(Integer.toString(expectedStatusCode), Integer.toString(actualStatusCode));
      return 1;
    }

    return 0;
  }

  public static int validateErrorResponseBody(Response actualResponse, ErrorResponseBody expectedResponse) {
    int failCount = 0;

    HashMap<String, ArrayList<String>> actualResponseBodyValues = actualResponse.body().as(ErrorResponseBody.class).getErrors();

    for(Map.Entry<String, ArrayList<String>> entry : expectedResponse.getErrors().entrySet()) {
      ArrayList<String> actual = actualResponseBodyValues.get(entry.getKey());
      ArrayList<String> expected = entry.getValue();

      for(int i = 0; i < expected.size(); i++) {
        if(!actual.get(i).equals(expected.get(i))) {
          System.out.println("FAILED ERROR MESSAGE VALIDATION: " + entry.getKey());
          PrintUtilities.printExpectedVsActual(expected.get(i), actual.get(i));
          failCount++;
        }
      }
    }

    return failCount;
  }

  public static void analyzeTestCaseResults(int failCount) {
    Assert.assertEquals(failCount, 0);
    if(failCount == 0) {
      System.out.println("Test Result: pass");
    }

  }
}
