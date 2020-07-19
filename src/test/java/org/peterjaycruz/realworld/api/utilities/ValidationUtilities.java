package org.peterjaycruz.realworld.api.utilities;

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
}
