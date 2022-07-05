package utils;

import com.github.javafaker.Faker;

public class DataGenerator {
    private static Faker faker = new Faker();

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getPassword() {
        return faker.internet().password();
    }

    public static String getText(
            int minimumLength, int maximumLength, boolean includeUppercase, boolean includeNumber) {
        try {
            return faker
                    .lorem()
                    .characters(minimumLength, maximumLength, includeUppercase, includeNumber);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.err.println("minimumLength and MaximumLength cannot be equals");
        }
        return null;
    }
}
