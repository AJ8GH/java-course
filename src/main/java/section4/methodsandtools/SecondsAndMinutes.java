package section4.methodsandtools;

public class SecondsAndMinutes {
    private static final String INVALID_VALUE_MESSAGE = "Invalid value";

    public static void main(String[] args) {
        System.out.println(getDurationString(120, 30));
        System.out.println(getDurationString(7230));
        System.out.println(getDurationString(930293842));
        System.out.println(getDurationString(346, 2));
    }

    private static String getDurationString(int minutes, int seconds) {
        if (minutes < 0 || seconds < 0 || seconds > 59) return INVALID_VALUE_MESSAGE;
        String hours = String.format("%02d", minutes / 60) + "h";
        String remainingMinutes = String.format("%02d", minutes % 60) + "m";
        String stringSeconds = String.format("%02d", seconds) + "s";
        return hours + " " +  remainingMinutes + " " + stringSeconds;
    }

    private static String getDurationString(int seconds) {
        if (seconds < 0) return INVALID_VALUE_MESSAGE;
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return getDurationString(minutes, remainingSeconds);
    }
}
