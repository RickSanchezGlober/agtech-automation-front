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
    public static String getNumber(int size) {
        return faker.number().digits(size);
    }
    public static String getPassword(int minimumLength, int maximumLength, boolean includeUppercase, boolean includeSpecial, boolean includeDigit) {
        if (includeSpecial) {
            char[] password = faker.lorem().characters(minimumLength, maximumLength, includeUppercase, includeDigit).toCharArray();
            char[] special = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};
            for (int i = 0; i < faker.random().nextInt(minimumLength); i++) {
                password[faker.random().nextInt(password.length)] = special[faker.random().nextInt(special.length)];
            }
            return new String(password);
        } else {
            return faker.lorem().characters(minimumLength, maximumLength, includeUppercase, includeDigit);
        }
    }
}
