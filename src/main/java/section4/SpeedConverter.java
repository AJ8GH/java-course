package section4;

public class SpeedConverter {
    static final double KM_IN_MILES = 1.609;

    public static long toMilesPerHour(double kilometersPerHour) {
        if (kilometersPerHour < 0) return -1L;
        return Math.round(kilometersPerHour / KM_IN_MILES);
    }

    public static void printConversion(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            System.out.println("Invalid Value");
        } else {
            System.out.println(kilometersPerHour + " km/h = " + toMilesPerHour(kilometersPerHour) + " mi/h");
        }
    }
}
