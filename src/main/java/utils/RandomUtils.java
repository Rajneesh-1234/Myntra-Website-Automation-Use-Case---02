package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    public static String randomFirstName() {
        return RandomStringUtils.randomAlphabetic(6);
    }
    public static String randomLastName() {
        return RandomStringUtils.randomAlphabetic(8);
    }
    public static String randomFullName() {
        return randomFirstName() + " " + randomLastName();
    }
    public static String randomEmail() {
        return RandomStringUtils.randomAlphabetic(5) + "@gmail.com";
    }
    public static String randomPhoneNumber() {
        return RandomStringUtils.randomNumeric(10);
    }
    public static String randomPassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
    public static String randomAddress() {
        return RandomStringUtils.randomAlphanumeric(12);
    }
    public static String randomZipCode() {
        return RandomStringUtils.randomNumeric(6);
    }
}