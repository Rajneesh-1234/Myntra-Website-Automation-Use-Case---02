package utils;

import org.testng.asserts.SoftAssert;

public class AssertionUtil {

    private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    public static void init() {
        softAssert.set(new SoftAssert());
    }

    public static void assertTrue(boolean condition, String message) {
        softAssert.get().assertTrue(condition, message);
    }

    public static void assertAll() {
        softAssert.get().assertAll();
        softAssert.remove();
    }
}