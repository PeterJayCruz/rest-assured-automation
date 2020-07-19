package org.peterjaycruz.realworld.api.utilities;

public class PrintUtilities {

  public static void printDivider() {
    System.out.println("==================================================");
  }

  public static void printExpectedVsActual(String expected, String actual) {
    System.out.println("expected: " + expected + " | actual: " + actual);
  }

  // temp method to print to console, will update once log4j is set up
  public static void printToConsole(String message) {
    System.out.println(message);
  }
}
