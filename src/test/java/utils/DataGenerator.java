package utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Calendar;

public class DataGenerator {
    private static Faker faker = new Faker();

    public static String getFullName() {
        return faker.name().fullName();
    }

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getPassword() {
        return faker.internet().password();
    }

    public static String getWrongEmail() {
        int valorDado = (int) Math.floor(Math.random() * 3 + 1);
        String wrongMail = "";
        switch (valorDado) {
            case 1:
                wrongMail = faker.animal().name();
                break;
            case 2:
                wrongMail = faker.name().lastName();
                break;
            case 3:
                wrongMail = faker.internet().password();
                break;
            case 4:
                wrongMail = faker.slackEmoji().emoji();
                break;
            default:
                wrongMail = "wrongEmail.Hey";
        }
        return wrongMail;
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

    public static String getCellPhoneNumber(int size) {
        return faker.number().digits(size);
    }

    public static String getNumber(int size) {
        return faker.number().digits(size);
    }


    public static String getPassword(int size) {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static String getDate() {
        // MM/DD/AAAA
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(faker.date().birthday());
        String date = "";

        if (calendar.get(Calendar.MONTH) < 10) {
            date += "0" + calendar.get(Calendar.MONTH);
        } else {
            date += "" + calendar.get(Calendar.MONTH);
        }
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            date += "0" + calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            date += "" + calendar.get(Calendar.DAY_OF_MONTH);
        }
        date += "" + calendar.get(Calendar.YEAR);
        return date;
    }
}
