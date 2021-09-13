package section4.methodsandtools;

public class MinutesToYearsAndDays {
    public static final String INVALID_INPUT_MESSAGE = "Invalid Value";
    public static final long MINUTES_IN_DAY = 1440;
    public static final long MINUTES_IN_YEAR = MINUTES_IN_DAY * 365;

    public static void printYearsAndDays(long minutes) {
        if (minutes < 0) {
            System.out.println(INVALID_INPUT_MESSAGE);
        } else {
            long years = minutes / MINUTES_IN_YEAR;
            long yearRemainder = minutes % MINUTES_IN_YEAR;
            long days = yearRemainder / MINUTES_IN_DAY;
            long remainingMinutes = yearRemainder % MINUTES_IN_DAY;

            System.out.println(minutes + " min = " + years + " y and " + days + " d");
        }
    }
}
