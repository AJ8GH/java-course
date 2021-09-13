package section4.methodsandtools;

public class DecimalComparator{
    public static boolean areEqualByThreeDecimalPlaces(double double1, double double2) {
        int int1 = (int) (double1 * 1000);
        int int2 = (int) (double2 * 1000);
        return int1 == int2;
    }
}
