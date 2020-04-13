package commm.empoyeeapi.utiliities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

    // Below are java methods which will create random string/number etc..

    public static String empName() {
        // RandomStringUtils is java method which will create one string with one character added with John
        String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("John" + generatedString);
    }

    public static String empSal() {
        // RandomStringUtils is java method which will create 5 digits number randomly in between 0 to 9
        String generatedString = RandomStringUtils.randomNumeric(5);
        return (generatedString);
    }

    public static String empAge() {
        // RandomStringUtils is java method which will create 2 digits randomly in between 0 to 9
        String generatedString = RandomStringUtils.randomNumeric(2);
        return (generatedString);
    }

}
